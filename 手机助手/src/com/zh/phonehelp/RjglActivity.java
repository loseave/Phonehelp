package com.zh.phonehelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.phonehelper.R;
import com.zh.phonehelp.utils.CommonUtil;
import com.zh.phonehelp.utils.MemoryManager;
import com.zh.phonehelp.view.MyAutoProgressBar;
import com.zh.phonehelp.view.MySoftBall;

public class RjglActivity extends Activity {
	MySoftBall sb;
	MyAutoProgressBar autoProgressBar;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rjgl);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		sb = (MySoftBall) findViewById(R.id.mySoftBall1);
		long num = MemoryManager.getPhoneFreeRamMemory(RjglActivity.this);
		long all = MemoryManager.getPhoneTotalRamMemory();
		int a = (int) (((double) num / all) * 360);
		sb.setAutoArc(a);
		autoProgressBar = (MyAutoProgressBar) findViewById(R.id.soft_progressbar);
		int b = (int) (((double) num / all) * 100);
		autoProgressBar.autoAddProgress(b);
		tv = (TextView) findViewById(R.id.tvrl);
		tv.setText("��������/��������" + CommonUtil.getFileSize(num) + "/"
				+ CommonUtil.getFileSize(all));
	}

	String name;

	public void softclick(View v) {
		switch (v.getId()) {
		case R.id.rl1:
			name="ȫ�����";
			break;

		case R.id.rl2:
			name="ϵͳ���";
			break;
		case R.id.rl3:
			name="�û����";
			break;
		}
		Intent it =new Intent(RjglActivity.this,SoftcontentActivity.class);
		it.putExtra("name", name);
		startActivity(it);
		finish();
	}
}
