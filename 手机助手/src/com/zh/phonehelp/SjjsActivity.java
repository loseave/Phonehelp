package com.zh.phonehelp;

import java.util.ArrayList;
import java.util.List;

import com.example.phonehelper.R;
import com.zh.phonehelp.adapter.SpeedAdapter;
import com.zh.phonehelp.entity.Speed;
import com.zh.phonehelp.utils.CommonUtil;
import com.zh.phonehelp.utils.MemoryManager;
import com.zh.phonehelp.utils.MobileManager;
import com.zh.phonehelp.view.MyAutoProgressBar;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SjjsActivity extends Activity {
	TextView tv1, tv2, tv3;
	MyAutoProgressBar progressBar;
	Button btn1, btn2;
	ListView lv;
	CheckBox cb;
	MobileManager manager;
	ArrayList<Speed> list;
	SpeedAdapter adapter;
	ImageView iv;
	ProgressBar bar;

	ActivityManager activityManager;
	PackageManager packageManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sjjs);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		list = new ArrayList<Speed>();
		manager = MobileManager.getPhoneManager(this);
		tv1 = (TextView) findViewById(R.id.sjjstv1);
		tv2 = (TextView) findViewById(R.id.sjjstv2);
		tv3 = (TextView) findViewById(R.id.sjjstv3);
		tv1.setText(manager.getPhoneInfo().getPhoneName1());
		tv2.setText(manager.getPhoneInfo().getPhoneCPUName());
		bar = (ProgressBar) findViewById(R.id.vbar);
		btn1 = (Button) findViewById(R.id.btnyjql);
		iv = (ImageView) findViewById(R.id.sjjsback);
		activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		packageManager=getPackageManager();
		
		init2();
		
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SjjsActivity.this, MenuActivity.class));
			}
		});
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).isChecked()) {
						activityManager.killBackgroundProcesses(list.get(j)
								.getPackagename());
					}
				}
				asyGetData();
				init2();
			}

		});

		lv = (ListView) findViewById(R.id.sjjslv);
		asyGetData();
		cb = (CheckBox) findViewById(R.id.yjjscb);
		cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				for (int j = 0; j < list.size(); j++) {
					list.get(j).setChecked(arg1);
				}
				adapter.notifyDataSetChanged();
			}
		});

	}

	private void init2() {
		long sum = MemoryManager.getPhoneTotalRamMemory();
		long free = MemoryManager.getPhoneFreeRamMemory(this);
		long used = sum - free;
		tv3.setText(CommonUtil.getFileSize(used) + "/"
				+ CommonUtil.getFileSize(sum));
		progressBar = (MyAutoProgressBar) findViewById(R.id.myAutoProgressBar1);
		int i = (int) (((double) used / sum) * 100);
		progressBar.autoAddProgress(i);
	}

	public void asyGetData() {
		list = new ArrayList<Speed>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<RunningAppProcessInfo> runApplist = activityManager
						.getRunningAppProcesses();
				List<PackageInfo> allApplist = packageManager
						.getInstalledPackages(packageManager.GET_UNINSTALLED_PACKAGES);
				for (PackageInfo packageInfo : allApplist) {
					for (int i = 0; i < runApplist.size(); i++) {
						if (packageInfo.packageName.equals(runApplist.get(i).processName)) {
							if (!packageInfo.packageName
									.equals("com.zh.phonehelp")
									&& runApplist.get(i).importance >= ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
								Speed sd = new Speed();
								sd.setIcon(packageInfo.applicationInfo
										.loadIcon(packageManager));
								sd.setName(packageInfo.applicationInfo
										.loadLabel(packageManager) + "");
								sd.setPackagename(packageInfo.packageName);

								MemoryInfo[] memoryInfos = activityManager
										.getProcessMemoryInfo(new int[] { runApplist
												.get(i).pid });
								MemoryInfo memoryInfo = memoryInfos[0];
								int memory = memoryInfo.getTotalPrivateDirty() * 1024;
								long m = (long) memory;
								sd.setSize(m);
								list.add(sd);
								Log.i("draft", "a");
								break;
							}
						}
					}
				}
				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.i("draft", "b");
						adapter = new SpeedAdapter(list, SjjsActivity.this);
						lv.setAdapter(adapter);
						bar.setVisibility(View.GONE);
					}
				});
			}
		}).start();
	}

	Handler handler = new Handler();
}
