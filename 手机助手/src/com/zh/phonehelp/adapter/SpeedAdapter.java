package com.zh.phonehelp.adapter;

import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonehelper.R;
import com.zh.phonehelp.entity.Speed;
import com.zh.phonehelp.utils.CommonUtil;

public class SpeedAdapter extends MybaseAdapter<Speed> {
	
	public SpeedAdapter(ArrayList<Speed> list,Context context){
		super(list,context);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		convertView =inflater.inflate(R.layout.sjjslistcontent,null);
		CheckBox cb=(CheckBox) convertView.findViewById(R.id.sjjscb);
		TextView tv1=(TextView)convertView.findViewById(R.id.jslisttv1);
		TextView tv2=(TextView)convertView.findViewById(R.id.jslisttv2);
		ImageView iv=(ImageView) convertView.findViewById(R.id.sjjsiv);
		iv.setImageDrawable(list.get(position).getIcon());
		tv1.setText(list.get(position).getName());
		tv2.setText(CommonUtil.getFileSize(list.get(position).getSize()));
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				list.get(position).setChecked(arg1);
			}
		});
		cb.setChecked(list.get(position).isChecked());
		return convertView;
	}
}
