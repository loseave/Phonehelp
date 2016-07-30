package com.zh.phonehelp.entity;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class Soft {
	boolean ischecked;
	String name;
	String packagename;
	Drawable drawable;
	Intent intent;

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public Soft(boolean ischecked, String name, String packagename,
			Drawable drawable, Intent intent) {
		super();
		this.ischecked = ischecked;
		this.name = name;
		this.packagename = packagename;
		this.drawable = drawable;
		this.intent = intent;
	}

	public Soft() {
		super();
	}

}
