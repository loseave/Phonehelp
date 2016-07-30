package com.zh.phonehelp;

import com.zh.phonehelp.utils.*;
import com.zh.phonehelp.view.AutoText;
import com.zh.phonehelp.view.MyAutoProgressBar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.phonehelper.R;
import com.zh.phonehelp.adapter.ClearlistAdapter;
import com.zh.phonehelp.adapter.SjjcAdapter;
import com.zh.phonehelp.entity.Clear;
import com.zh.phonehelp.entity.Sjjc;

public class SjjcActivity extends Activity {
	ImageView iv;
	ListView lv;
	ArrayList<Sjjc> list;
	SjjcAdapter adapter;
	MobileManager man;
	MemoryManager mem;
	MyAutoProgressBar autoBar;
	AutoText autoText;
	MyBatteryBroadcastReceiver receiver;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sjjc);

		autoBar = (MyAutoProgressBar) findViewById(R.id.phoneCheck_bar);
		autoText = (AutoText) findViewById(R.id.phoneCheck_baifen);

		receiver = new MyBatteryBroadcastReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(receiver, filter);

		iv = (ImageView) findViewById(R.id.sjjcback);
		lv = (ListView) findViewById(R.id.sjjclv);

		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SjjcActivity.this, MenuActivity.class));
				finish();
			}
		});

		man = MobileManager.getPhoneManager(SjjcActivity.this);
		list = new ArrayList<Sjjc>();
		for (int i = 1; i < 7; i++) {
			Sjjc l = new Sjjc();
			switch (i) {
			case 1:
				l.setDrawable(getResources().getDrawable(R.drawable.setting1));
				l.setT1("设备名称:" + man.getPhoneInfo().getPhoneName1());
				l.setT2("系统版本:" + man.getSystemInfo().getPhoneSystemVersion());
				break;
			case 2:
				l.setDrawable(getResources().getDrawable(R.drawable.setting2));
				l.setT1("全部运行内存:"
						+ CommonUtil.getFileSize(mem.getPhoneTotalRamMemory()));
				l.setT2("全部剩余内存:"
						+ CommonUtil.getFileSize((mem
								.getPhoneFreeRamMemory(SjjcActivity.this))));
				break;
			case 3:
				l.setDrawable(getResources().getDrawable(R.drawable.setting3));
				l.setT1("cpu名称:" + man.getPhoneInfo().getPhoneCPUName());
				l.setT2("cpu数量:" + man.getPhoneInfo().getPhoneCpuNumber());
				break;
			case 4:
				l.setDrawable(getResources().getDrawable(R.drawable.setting4));
				l.setT1("手机分辨率:" + man.getPhoneInfo().getResolution());
				l.setT2("相机分辨率:" + man.getPhoneInfo().getCameraResolution());
				break;
			case 5:
				l.setDrawable(getResources().getDrawable(R.drawable.setting5));
				l.setT1("基带版本:"
						+ man.getSystemInfo().getPhoneSystemBasebandVersion());
				l.setT2("IMEI:" + man.getPhoneInfo().getPhoneIMEI());
				break;
			case 6:
				l.setDrawable(getResources().getDrawable(R.drawable.setting1));
				l.setT1("设置版本号:"
						+ man.getSystemInfo().getPhoneSystemVersionID());
				l.setT2("CPU当前频率:"
						+ man.getPhoneInfo().getPhoneCpuCurrentFreq());
				break;
			}
			list.add(l);
		}

		adapter = new SjjcAdapter(list, SjjcActivity.this);
		lv.setAdapter(adapter);

	}

	class MyBatteryBroadcastReceiver extends BroadcastReceiver {
		int dlNum = 0;

		@Override
		public void onReceive(Context ct, Intent it) {
			// TODO Auto-generated method stub
			if (it.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				Bundle bundle = it.getExtras();
				int num = bundle.getInt(BatteryManager.EXTRA_LEVEL);
				int numAll = bundle.getInt(BatteryManager.EXTRA_SCALE);
				if (num != dlNum) {
					dlNum = num;
					autoBar.autoAddProgress(num);
					autoText.autoAddProgress(num);
				}
			}
		}

	}

}
