package com.zh.phonehelp;

import java.util.List;

import com.example.phonehelper.R;
import com.zh.phonehelp.utils.MemoryManager;
import com.zh.phonehelp.view.MyMainBall;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity implements OnClickListener {
	Button iv1;
	Button iv2;
	Button iv3;
	Button iv4;
	Button iv5;
	ImageView iv;
	
	ActivityManager activityManager;
	private MyMainBall ball;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		activityManager=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		ball =(MyMainBall) findViewById(R.id.myMainBall1);
		tv=(TextView) findViewById(R.id.jdtv);
		getAutoRam();
		
		iv=(ImageView) findViewById(R.id.jdiv);
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getAutoRam();
			}
		});
		
		
		

		iv1 = (Button) findViewById(R.id.ll1);
		iv1.setOnClickListener(this);
		iv2 = (Button) findViewById(R.id.ll2);
		iv2.setOnClickListener(this);
		iv3 = (Button) findViewById(R.id.ll3);
		iv3.setOnClickListener(this);
		iv4 = (Button) findViewById(R.id.ll4);
		iv4.setOnClickListener(this);
		iv5 = (Button) findViewById(R.id.ll5);
		iv5.setOnClickListener(this);

	}

	public static boolean thread=false;
	
	public void getAutoRam() {
		// TODO Auto-generated method stub
		if(thread){
			return;
		}
		List<RunningAppProcessInfo> runAppList=activityManager.getRunningAppProcesses();
		for (RunningAppProcessInfo runapp : runAppList) {
			if(runapp.importance>=RunningAppProcessInfo.IMPORTANCE_SERVICE){
				activityManager.killBackgroundProcesses(runapp.processName);
			}
		}
		long aRam=MemoryManager.getPhoneTotalRamMemory();
		long fRam=MemoryManager.getPhoneFreeRamMemory(MenuActivity.this);
		
		final int tB1=(int)((double)fRam/(double)aRam*100);
		final int aB1=(int)((double)fRam/(double)aRam*360);
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ball.myball(aB1);
				tv.setText(tB1+"%");
			}
		});
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll1:
			startActivity(new Intent(MenuActivity.this, SjjsActivity.class));
			break;
		case R.id.ll2:
			startActivity(new Intent(MenuActivity.this, RjglActivity.class));
			break;
		case R.id.ll3:
			startActivity(new Intent(MenuActivity.this, SjjcActivity.class));
			break;
		case R.id.ll4:
			startActivity(new Intent(MenuActivity.this, TxdqActivity.class));
			break;
		case R.id.ll5:
			startActivity(new Intent(MenuActivity.this, LjqlActivity.class));
			break;

		default:
			break;
		}
	}

	private boolean isback = true;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isback) {
				isback=false;
				Toast.makeText(MenuActivity.this, "再次点击退出", 0).show();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isback=true;
					}
				}).start();
			}else{
				finish();
			}
		}
		return isback;
	}
}
