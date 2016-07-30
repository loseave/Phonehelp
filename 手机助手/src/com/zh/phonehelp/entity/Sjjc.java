package com.zh.phonehelp.entity;

import android.graphics.drawable.Drawable;

public class Sjjc {
	String t1;
	String t2;
	Drawable drawable;

	public Sjjc(String t1, String t2, Drawable drawable) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.drawable = drawable;
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public Sjjc() {
		super();
	}

}
