package com.zh.phonehelp.adapter;

import java.util.ArrayList;

import com.example.phonehelper.R;
import com.example.phonehelper.R.layout;
import com.zh.phonehelp.entity.PhoneEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PhonelistAdapter extends BaseAdapter {
	ArrayList<PhoneEntity> list;
	LayoutInflater inflater;
	Context context;

	public PhonelistAdapter() {
		super();
	}

	public PhonelistAdapter(ArrayList<PhoneEntity> list,Context context) {
		super();
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int posi, View cv, ViewGroup arg2) {
		// TODO Auto-generated method stub
		cv = inflater.inflate(R.layout.listcontent, null);
		TextView name = (TextView) cv.findViewById(R.id.tvname);
		TextView phone = (TextView) cv.findViewById(R.id.tvphone);
		name.setText(list.get(posi).getName());
		phone.setText(list.get(posi).getPhone());
		return cv;
	}

}
