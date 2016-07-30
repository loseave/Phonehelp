package com.zh.phonehelp.adapter;

import java.util.ArrayList;

import com.example.phonehelper.R;
import com.zh.phonehelp.entity.Clear;
import com.zh.phonehelp.entity.PhoneEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ClearlistAdapter extends BaseAdapter {
	ArrayList<Clear> list;
	LayoutInflater inflater;
	Context context;

	public ClearlistAdapter(ArrayList<Clear> list, Context context) {
		super();
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	public ClearlistAdapter() {
		super();
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
	public View getView(final int posi, View cv, ViewGroup arg2) {
		// TODO Auto-generated method stub
		cv = inflater.inflate(R.layout.yjqllistcontent, null);
		CheckBox cb = (CheckBox) cv.findViewById(R.id.listcb);
		ImageView iv = (ImageView) cv.findViewById(R.id.listiv);
		TextView name = (TextView) cv.findViewById(R.id.listtv1);
		TextView size = (TextView) cv.findViewById(R.id.listtv2);

		name.setText(list.get(posi).getName());
		size.setText(com.zh.phonehelp.utils.CommonUtil.getFileSize(list.get(
				posi).getSize()));
		iv.setImageDrawable(list.get(posi).getIcon());
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				list.get(posi).setIschecked(arg1);
			}
		});
		
		cb.setChecked(list.get(posi).isIschecked());
		return cv;
	}

}
