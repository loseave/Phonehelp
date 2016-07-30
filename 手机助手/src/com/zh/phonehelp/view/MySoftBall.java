package com.zh.phonehelp.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MySoftBall extends View {

	Paint paintbg, paintBall;
	RectF rectF;
	Context context;

	public MySoftBall(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MySoftBall(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MySoftBall(Context context) {
		super(context);
		init(context);
		// TODO Auto-generated constructor stub
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		this.context = context;
		paintBall = new Paint();
		paintbg = new Paint();
		paintbg.setColor(Color.YELLOW);
		paintBall.setColor(Color.GREEN);
		paintBall.setAntiAlias(true);
		paintbg.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int h = MeasureSpec.getSize(heightMeasureSpec);
		int w = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(w, h);
		rectF = new RectF(0, 0, w, h);
	}

	int zong = 0;
	int free;

	public void setAutoArc(final int du) {
		final Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (zong <= 360) {
					zong++;
					postInvalidate();
				} else {
					timer.cancel();
				}
			}
		};
		timer.schedule(task, 10, 10);

		Timer timer2 = new Timer();
		TimerTask task2 = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (free <= du) {
					free++;
					postInvalidate();
				}
			}
		};
		timer2.schedule(task2, 15, 15);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawArc(rectF, -90, zong, true, paintbg);
		canvas.drawArc(rectF, -90, free, true, paintBall);
	}

}
