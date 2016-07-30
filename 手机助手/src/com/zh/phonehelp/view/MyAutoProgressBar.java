package com.zh.phonehelp.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class MyAutoProgressBar extends ProgressBar {


	
	public MyAutoProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyAutoProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyAutoProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	//µÝÔö
	public void autoAddProgress(final int end){
		setProgress(0);
		final Timer timer=new Timer();
		TimerTask task=new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				setProgress(getProgress()+1);
				if(getProgress()>=end){
					timer.cancel();
					setProgress(end);
				}
				postInvalidate();
			}
			
		};
		timer.schedule(task, 40,40);
	}
	
	//µÝ¼õ
	public void autoDownProgress(int max,final int end){
		setProgress(max);
		final Timer timer=new Timer();
		TimerTask task=new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				setProgress(getProgress()-1);
				if(getProgress()<=end){
					timer.cancel();
					setProgress(end);
				}
				postInvalidate();
			}
			
		};
		timer.schedule(task, 40,40);
	}
}
