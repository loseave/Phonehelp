package com.zh.phonehelp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.example.phonehelper.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class BootActivity extends Activity {
	ViewPager vp;
	ArrayList<View> list;

	// Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.boot);

		
		SharedPreferences preferences = getSharedPreferences("firststart",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		
	    // 判断是不是首次登录，
		if (preferences.getBoolean("firststart", false)) {
			// 将登录标志位设置为false，下次登录时不在显示首次登录界面
			Intent intent = new Intent(BootActivity.this, AnimActivity.class);
			startActivity(intent);
			finish();
		}
		editor.putBoolean("firststart", true);
		editor.commit();
		
		vp = (ViewPager) findViewById(R.id.bootvp);
		list = new ArrayList<View>();
		list.add(getLayoutInflater().inflate(R.layout.boot_a, null));
		list.add(getLayoutInflater().inflate(R.layout.boot_b, null));
		list.add(getLayoutInflater().inflate(R.layout.boot_c, null));

		vp.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return list.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(list.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View v = list.get(position);
				container.addView(v);
				return v;
			}

		});

		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 2) {
					startActivity(new Intent(BootActivity.this,
							AnimActivity.class));
					finish();
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
