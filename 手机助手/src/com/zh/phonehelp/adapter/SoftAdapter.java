package com.zh.phonehelp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.phonehelper.R;
import com.zh.phonehelp.entity.Soft;
import com.zh.phonehelp.utils.CommonUtil;

public class SoftAdapter extends MybaseAdapter<Soft> {

	public SoftAdapter(ArrayList<Soft> list, Context context) {
		super(list, context);
		// TODO Auto-generated constructor stub
	}

	class HoldView {
		TextView name;
		TextView packagename;
		ImageView icon;
		CheckBox cb;
	}

	HoldView hv;

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		hv = new HoldView();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.softcontentlist, null);
			hv.cb = (CheckBox) convertView.findViewById(R.id.sclcb);
			hv.icon = (ImageView) convertView.findViewById(R.id.scliv);
			hv.name = (TextView) convertView.findViewById(R.id.scltv1);
			hv.packagename = (TextView) convertView.findViewById(R.id.scltv2);
			convertView.setTag(hv);
		}else{
			hv=(HoldView)convertView.getTag();
		}
		hv.icon.setImageDrawable(list.get(position).getDrawable());
		hv.name.setText(list.get(position).getName());
		hv.packagename.setText(list.get(position).getPackagename());
		hv.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				list.get(position).setIschecked(arg1);
			}
		});
		hv.cb.setChecked(list.get(position).isIschecked());
		return convertView;
	}
}
