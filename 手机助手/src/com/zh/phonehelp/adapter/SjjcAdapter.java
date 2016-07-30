package com.zh.phonehelp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonehelper.R;
import com.zh.phonehelp.entity.Sjjc;

public class SjjcAdapter extends MybaseAdapter<Sjjc> {

	public SjjcAdapter(ArrayList<Sjjc> list, Context context) {
		super(list, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.sjjclistcontent, null);
		ImageView iv = (ImageView) convertView.findViewById(R.id.imgsjjc);
		TextView tv1 = (TextView) convertView.findViewById(R.id.sjjctv1);
		TextView tv2 = (TextView) convertView.findViewById(R.id.sjjctv2);

		iv.setImageDrawable(list.get(position).getDrawable());
		tv1.setText(list.get(position).getT1());
		tv2.setText(list.get(position).getT2());

		if (position % 3 == 0) {
			iv.setBackgroundResource(R.drawable.iconshape1);
		} else if (position % 3 == 1) {
			iv.setBackgroundResource(R.drawable.iconshape2);
		} else if (position % 3 == 2) {
			iv.setBackgroundResource(R.drawable.iconshape3);
		}
		return convertView;
	}
}
