package com.example.teatapp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainGroupActivity extends ActivityGroup {
	private ScrollView container = null;
	private TextView custTitle = null;
	private ImageView btnModuleHome = null;
	private ImageView contactModuleHome = null;
	private ImageView fileModuleHome = null;
	private ImageView btnScanQR=null;

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

		btnScanQR=(ImageView)findViewById(R.id.btnScanQR);
		
		btnScanQR.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			}
		});
		
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

	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				
				HttpClient httpClient = new DefaultHttpClient();
				String uri = getResources().getString(R.string.server_uri);
				uri+="loginFormScan.do";
				
				StringBuffer address = new StringBuffer(uri);
				address.append("?id=" + contents);
				HttpPost httpPost = new HttpPost(address.toString());
				try {
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String result = EntityUtils.toString(httpResponse
								.getEntity());
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Handle successful scan
				Log.i("aa", " 条形码为:" + contents + " 条码类型为: " + format);// 利用页面的textveiw显示扫描后的结果

			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				Log.i("aa", " 扫描失败!");
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_group, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.action_exit2){
			Sysutil mSysUtil= new Sysutil(MainGroupActivity.this);  
            mSysUtil.exit();  
		}
		return super.onOptionsItemSelected(item);
	}

}
