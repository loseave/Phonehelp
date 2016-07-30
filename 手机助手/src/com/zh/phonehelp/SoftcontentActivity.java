package com.zh.phonehelp;

import java.util.ArrayList;

import com.example.phonehelper.R;
import com.example.phonehelper.R.layout;
import com.example.phonehelper.R.menu;
import com.zh.phonehelp.adapter.SoftAdapter;
import com.zh.phonehelp.entity.Soft;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SoftcontentActivity extends Activity {
	ImageView iv;
	TextView tv;
	ListView lv;
	ArrayList<Soft> list;
	PackageManager packageManager;
	SoftAdapter adapter;
	ProgressBar bar;
	Button clr;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_softcontent);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		iv = (ImageView) findViewById(R.id.ctback);
		tv = (TextView) findViewById(R.id.cttv);
		lv = (ListView) findViewById(R.id.ctlv);
		list = new ArrayList<Soft>();
		clr=(Button) findViewById(R.id.ctbtn);
		bar=(ProgressBar) findViewById(R.id.ctbar);
		packageManager = getPackageManager();
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SoftcontentActivity.this,
						RjglActivity.class));
				finish();
			}
		});

		Intent it = getIntent();
		name = it.getStringExtra("name");
		tv.setText(name);

		GetDataSoft();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = list.get(position).getIntent();
				if (intent != null
						&& list.get(position).getPackagename()
								.equals("com.zh.phonehelp")) {
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
				}
			}
		});

		clr.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).isIschecked()) {
						if (!list.get(i).getPackagename()
								.equals("com.zh.phonehelp")) {
							Intent it = new Intent(Intent.ACTION_DELETE);
							Uri uri = Uri.parse("package:"
									+ list.get(i).getPackagename());
							it.setData(uri);
							startActivityForResult(it, 111);
						}
					}
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 111) {
			init();
		}
	}

	public Handler handler = new Handler();

	public void GetDataSoft() {
		// TODO Auto-generated method stub
		list = new ArrayList<Soft>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ArrayList<PackageInfo> applist = (ArrayList<PackageInfo>) packageManager
						.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
				if ("全部软件".equals(name)) {
					for (PackageInfo packageInfo : applist) {
						Soft st = new Soft();
						st.setDrawable(packageInfo.applicationInfo
								.loadIcon(packageManager));
						st.setName(packageInfo.applicationInfo
								.loadLabel(packageManager) + "");
						st.setPackagename(packageInfo.packageName);
						Intent it = packageManager.getLaunchIntentForPackage(st
								.getPackagename());
						st.setIntent(it);
						list.add(st);
					}
				} else if ("系统软件".equals(name)) {
					for (PackageInfo packageInfo : applist) {
						int cF = packageInfo.applicationInfo.flags;
						int sF = ApplicationInfo.FLAG_SYSTEM
								| ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
						if ((sF & cF) != 0) {
							Soft st = new Soft();
							st.setDrawable(packageInfo.applicationInfo
									.loadIcon(packageManager));
							st.setName(packageInfo.applicationInfo
									.loadLabel(packageManager) + "");
							st.setPackagename(packageInfo.packageName);
							Intent it = packageManager
									.getLaunchIntentForPackage(st
											.getPackagename());
							st.setIntent(it);
							list.add(st);
						}
					}
				} else if ("用户软件".equals(name)) {
					for (PackageInfo packageInfo : applist) {
						int cF = packageInfo.applicationInfo.flags;
						int sF = ApplicationInfo.FLAG_SYSTEM
								| ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
						if ((sF & cF) == 0) {
							Soft st = new Soft();
							st.setDrawable(packageInfo.applicationInfo
									.loadIcon(packageManager));
							st.setName(packageInfo.applicationInfo
									.loadLabel(packageManager) + "");
							st.setPackagename(packageInfo.packageName);
							Intent it = packageManager
									.getLaunchIntentForPackage(st
											.getPackagename());
							st.setIntent(it);
							list.add(st);
						}
					}
			
				}
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						adapter=new SoftAdapter(list, SoftcontentActivity.this);
						lv.setAdapter(adapter);
						bar.setVisibility(View.GONE);
					}
				});
			}
		}).start();
	}
}
