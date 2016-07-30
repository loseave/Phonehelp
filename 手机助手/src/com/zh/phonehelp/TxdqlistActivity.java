package com.zh.phonehelp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.phonehelper.R;
import com.zh.phonehelp.adapter.PhonelistAdapter;
import com.zh.phonehelp.entity.PhoneEntity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TxdqlistActivity extends Activity {
	ArrayList<PhoneEntity> list = new ArrayList<PhoneEntity>();
	ListView lv;
	int pos;
	ImageView iv;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_txdqlist);

		tv = (TextView) findViewById(R.id.tvtxdq);
		lv = (ListView) findViewById(R.id.phonelist);
		
		iv = (ImageView) findViewById(R.id.back123);
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(TxdqlistActivity.this,
						TxdqActivity.class));
				finish();
			}
		});

		
		PhonelistAdapter adapter = new PhonelistAdapter(list, this);
		lv.setAdapter(adapter);
		//

		Intent it = getIntent();
		String id = it.getStringExtra("id");
		String name = it.getStringExtra("name");
		Log.i("oracle", name);
		tv.setText(name); 
		list = getData(this, id);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub
				pos = position;
				AlertDialog.Builder bu = new AlertDialog.Builder(
						TxdqlistActivity.this);
				bu.setTitle("提示消息");
				bu.setMessage("是否进行拨号?");
				bu.setNegativeButton("是",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								startActivity(new Intent(Intent.ACTION_CALL,
										Uri.parse("tel:"
												+ list.get(pos).getPhone())));
							}
						});
				bu.setPositiveButton("否", null);

				bu.show();

			}
		});

	}

	private ArrayList<PhoneEntity> getData(Context context, String id) {
		// TODO Auto-generated method stub

		String datapath = "data" + File.separator + "data" + File.separator
				+ "com.example.phonehelper";
		String frompath = "db" + File.separator + "commonnum.db";
		File f = new File(datapath, "commonnum.db");
		
		AssetManager assetmanaget = context.getAssets();
		InputStream is;
		if (!f.exists()) {
			try {
				is = assetmanaget.open(frompath);

				BufferedInputStream input = new BufferedInputStream(is);
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(f));

				int count = 0;
				byte[] byt = new byte[1024];

				while ((count = input.read(byt)) != -1) {
					out.write(byt, 0, count);
					out.flush();
				}
				out.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (f.exists()) {
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(f, null);
			String sql = "select * from table" + id;
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				PhoneEntity pe = new PhoneEntity();

				pe.setId(cursor.getInt(0));
				pe.setName(cursor.getString(2));
				pe.setPhone(cursor.getString(1));
				list.add(pe);

			}
			cursor.close();
			db.close();
		}

		return list;

	}

}
