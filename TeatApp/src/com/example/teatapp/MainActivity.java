package com.example.teatapp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedSqlLiteObjects()
				.penaltyLog().penaltyDeath().build());
		Button button = (Button) findViewById(R.id.login);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText username = (EditText) findViewById(R.id.username);
				EditText pwd = (EditText) findViewById(R.id.password);

				String userName = username.getText().toString();
				String password = pwd.getText().toString();

				HttpClient httpClient = new DefaultHttpClient();
				String uri = getResources().getString(R.string.server_uri);
				uri+="login.do";
				StringBuffer address = new StringBuffer(uri);
				if (userName != null && !"".equals(userName)) {
					address.append("?username=" + userName);
				}
				if (password != null && !"".equals(password)) {
					address.append("&password=" + password);
				}

				HttpPost httpPost = new HttpPost(address.toString());
				try {
					
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						if ("Faild".equals(result)) {
							Toast.makeText(MainActivity.this,
									R.string.msg_wrong_pwd, Toast.LENGTH_LONG)
									.show();
						} else if ("Success".equals(result)) {
							 Intent i=new Intent();
							 i.setClass(MainActivity.this,MainGroupActivity.class);
							 startActivity(i);
							 
						}
					}
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, e.toString(),
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.actoin_exit){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	 protected void onStart() {  
	        int flag = getIntent().getIntExtra("flag", 0);  
	        if(flag == Sysutil.EXIT_APPLICATION){  
	            finish();  
	        }  
	        super.onResume();  
	          
	    }  
	

}
