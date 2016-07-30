package com.zh.phonehelp;

import com.example.phonehelper.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anima);

		Animation am = AnimationUtils.loadAnimation(this, R.anim.myset);
		ImageView im = (ImageView) findViewById(R.id.animimg);
		im.startAnimation(am);
		am.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AnimActivity.this, MenuActivity.class));
				overridePendingTransition(android.R.anim.slide_out_right,
						android.R.anim.slide_in_left);
				finish();
			}
		});

	}
}
