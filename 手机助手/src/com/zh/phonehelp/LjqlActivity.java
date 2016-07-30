package com.zh.phonehelp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.phonehelper.R;
import com.zh.phonehelp.adapter.ClearlistAdapter;
import com.zh.phonehelp.entity.Clear;
import com.zh.phonehelp.utils.CommonUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class LjqlActivity extends Activity {
	private ArrayList<Clear> list;
	private ImageView iv;
	private TextView tv;
	private ListView lv;
	private ProgressBar bar;
	private CheckBox cb;
	private Button btn;
	long allsize;
	ClearlistAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ljql);
		init();
	}

	private void init() {
		// 控件初始化
		iv = (ImageView) findViewById(R.id.backiv);
		tv = (TextView) findViewById(R.id.tvljdx);// 显示总共垃圾有多少
		lv = (ListView) findViewById(R.id.lvljql);
		bar = (ProgressBar) findViewById(R.id.barljql);
		cb = (CheckBox) findViewById(R.id.cbqx);
		btn = (Button) findViewById(R.id.yjqlbtn);
		list = new ArrayList<Clear>();

		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).isIschecked()) {
						File f = new File(list.get(i).getFilePath());
						delfile(f);
					}
				}
				asynClearData();
			}
		});

		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LjqlActivity.this, MenuActivity.class));
				finish();
			}
		});
		asynClearData();
		// getdate();// 填充数据

		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischeck) {
				for (Clear clear : list) {
					clear.setIschecked(ischeck);
				}
				adapter.notifyDataSetChanged();
			}
		});

	}

	private void getdate() {
		list.clear();
		// 复制clearpath.db文件
		String datapath = "data" + File.separator + "data" + File.separator
				+ "com.example.phonehelper";
		String frompath = "db" + File.separator + "clearpath.db";
		File f = new File(datapath, "clearpath.db");

		AssetManager assetmanaget = getAssets();
		InputStream is;
		if (!f.exists()) {
			try {
				is = assetmanaget.open(frompath);

				BufferedInputStream input = new BufferedInputStream(is);
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(f));

				int count = 0;
				byte[] byt = new byte[1024];

				while ((count = input.read(byt)) != -1) {
					out.write(byt, 0, count);
					out.flush();
				}
				out.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 数据库操作
		if (f.exists()) {
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(f, null);
			String sql = "select * from softdetail";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				Clear cr = new Clear();
				cr.setName(cursor.getString(1));
				cr.setAPKName(cursor.getString(3));
				cr.setFilePath(Environment.getExternalStorageDirectory()
						+ File.separator + cursor.getString(4));
				File file = new File(cr.getFilePath());
				Drawable icon = null;
				if (file.exists()) {
					try {
						icon = getPackageManager().getApplicationIcon(
								cr.getAPKName());
						cr.setIcon(icon);
						Log.i("draft", cr.getSize() + "");
						list.add(cr);
					} catch (NameNotFoundException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						icon = getResources().getDrawable(
								R.drawable.ic_launcher);
						cr.setIcon(icon);
						list.add(cr);

					}
				}

			}
			cursor.close();
			db.close();
		}
	}

	private static long getfilesize(File file) {
		long size = 0;
		if (!file.isDirectory()) {
			return file.length();
		}
		File files[] = file.listFiles();
		if (file != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					size += getfilesize(files[i]);
				} else {
					size += files[i].length();
				}
			}
		}
		return size;
	}

	private void asynClearData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				getdate();
				allsize = 0;
				for (int i = 0; i < list.size(); i++) {
					File ftemp = new File(list.get(i).getFilePath());
					long ltemp = getfilesize(ftemp);
					list.get(i).setSize(ltemp);
					allsize += ltemp;
				}
				runOnUiThread(new Runnable() {
					public void run() {

						adapter = new ClearlistAdapter(list, LjqlActivity.this);
						lv.setAdapter(adapter);// 初始化适配器以及加载适配器
						tv.setText(com.zh.phonehelp.utils.CommonUtil
								.getFileSize(allsize));
						bar.setVisibility(View.GONE);
						lv.setVisibility(View.VISIBLE);
					}
				});
			}
		}).start();
	}

	public static void delfile(File file) {
		if (!file.isDirectory()) {
			boolean b = file.delete();
		} else {
			File files[] = file.listFiles();
			if (file != null) {
				for (int i = 0; i < files.length; i++) {
					delfile(files[i]);
				}
				file.delete();
			}
		}
	}
}
