package com.zh.phonehelp.entity;

import android.graphics.drawable.Drawable;


public class Clear {
	private Drawable icon;// app的图标
	private String name;// app的名称
	private long size;// app的大小
	private boolean ischecked;// checkbox的选中状态
	private String APKName;// app的包名
	private String filePath; // app的路径

	public Clear() {
		super();
	}

	public Clear(Drawable icon, String name, long size, boolean ischecked,
			String aPKName, String filePath) {
		super();
		this.icon = icon;
		this.name = name;
		this.size = size;
		this.ischecked = ischecked;
		APKName = aPKName;
		this.filePath = filePath;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

	public String getAPKName() {
		return APKName;
	}

	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
