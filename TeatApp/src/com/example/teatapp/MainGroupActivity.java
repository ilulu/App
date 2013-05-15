package com.example.teatapp;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainGroupActivity extends ActivityGroup {
	private ScrollView container = null;
	private TextView custTitle = null;
	private ImageView btnModuleHome = null;
	private ImageView contactModuleHome = null;
	private ImageView fileModuleHome = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_group);
		// 获取中间的动态视图
		container = (ScrollView) findViewById(R.id.containerBody);
		custTitle = (TextView) findViewById(R.id.cust_title);
		btnModuleHome = (ImageView) findViewById(R.id.btnModule1);

		container.addView(getLocalActivityManager().startActivity(
				"ModuleHome",
				new Intent(MainGroupActivity.this, HomeActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView());

		btnModuleHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"ModuleHome",
						new Intent(MainGroupActivity.this, HomeActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
				custTitle.setText("Home");
				btnModuleHome.setImageResource(R.drawable.menu_home_h);
				contactModuleHome.setImageResource(R.drawable.menu_contact_d);
				fileModuleHome.setImageResource(R.drawable.menu_folder_d);
			}
		});

		contactModuleHome = (ImageView) findViewById(R.id.btnModule2);
		contactModuleHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"ModuleContact",
						new Intent(MainGroupActivity.this,
								ContactActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
				custTitle.setText("Contact");
				btnModuleHome.setImageResource(R.drawable.menu_home_d);
				contactModuleHome.setImageResource(R.drawable.menu_contact_h);
				fileModuleHome.setImageResource(R.drawable.menu_folder_d);
			}
		});

		fileModuleHome = (ImageView) findViewById(R.id.btnModule3);
		fileModuleHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"ModuleScan",
						new Intent(MainGroupActivity.this, ScanActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
				custTitle.setText("File");
				btnModuleHome.setImageResource(R.drawable.menu_home_d);
				contactModuleHome.setImageResource(R.drawable.menu_contact_d);
				fileModuleHome.setImageResource(R.drawable.menu_folder_h);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_group, menu);
		return true;
	}

}
