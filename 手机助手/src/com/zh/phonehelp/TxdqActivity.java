package com.zh.phonehelp;

import com.example.phonehelper.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TxdqActivity extends Activity {
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.txdq);
		iv = (ImageView) findViewById(R.id.btn_back);
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TxdqActivity.this, MenuActivity.class));
				finish();
			}
		});
	}

	private int id;
	private String name;

	public void txdq(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.btn_dcdh:
			id = 1;
			name="订餐电话";
			break;
		case R.id.btn_ggfw:
			id = 2;
			name="公共服务";
			break;
		case R.id.btn_yys:
			id = 3;
			name="运营商";
			break;
		case R.id.btn_kdfw:
			id = 4;
			name="快递服务";
			break;
		case R.id.btn_jpjd:
			id = 5;
			name="机票酒店";
			break;
		case R.id.btn_yhzq:
			id = 6;
			name="银行证券";
			break;
		case R.id.btn_bxfw:
			id = 7;
			name="保险服务";
			break;
		case R.id.btn_ppsh:
			id = 8;
			name="品牌售后";
			break;

		}

		Intent it = new Intent(this, TxdqlistActivity.class);
		it.putExtra("id", id+"");
		it.putExtra("name", name);
		startActivity(it);

	}

}
