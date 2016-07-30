package com.zh.phonehelp.view;

import java.util.Timer;
import java.util.TimerTask;

import com.zh.phonehelp.MenuActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyMainBall extends View {
	Paint paint;
	RectF rectF;
	int sweepAngle;
	
	public MyMainBall(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MyMainBall(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyMainBall(Context context) {
		super(context);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		paint=new Paint();
		paint.setTextSize(10);
		paint.setColor(Color.GREEN);
		paint.setAntiAlias(true);
	}
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int w=MeasureSpec.getSize(widthMeasureSpec);
		int h=MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(w, h);
		rectF=new RectF(0,0,w,h);
	}
	
	public void startball(final int sw) {
		// TODO Auto-generated method stub
		sweepAngle=0;
		final Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(sweepAngle<sw){
					sweepAngle++;
					postInvalidate();
				}else{
					timer.cancel();
					MenuActivity.thread=false;
				}
			}
		};
		timer.schedule(task, 15,15);
	}
	
	public void myball(final int sw) {
		// TODO Auto-generated method stub
		this.sweepAngle=sw;
		MenuActivity.thread=true;
		final Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(sweepAngle>=0){
					sweepAngle--;
					postInvalidate();
				}else{
					timer.cancel();
					startball(sw);
				}
			}
		};
		timer.schedule(task, 5,5);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawArc(rectF, -90, sweepAngle, true, paint);
	}
}
