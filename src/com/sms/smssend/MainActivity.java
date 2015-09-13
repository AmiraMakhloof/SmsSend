package com.sms.smssend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button send ,sendBuiltIn;
	EditText message;
	EditText number;
	String phoneNumber,sms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEventObject();
		
	}
	public void initView() {
		number = (EditText)findViewById(R.id.etNumber);
		message = (EditText)findViewById(R.id.etMessage);
		send = (Button)findViewById(R.id.btnSend);
		sendBuiltIn = (Button)findViewById(R.id.btnIntent);
	}

	public void initEventObject() {
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phoneNumber=number.getText().toString();
				sms=message.getText().toString();
				smsSend(phoneNumber,sms);
				Toast.makeText(MainActivity.this, "sms sent", Toast.LENGTH_LONG);
			}
		});
		sendBuiltIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phoneNumber=number.getText().toString();
				sms=message.getText().toString();
				Intent i = new Intent(android.content.Intent.ACTION_VIEW);
				i.putExtra("address", phoneNumber);
				i.putExtra("sms_body", sms);
				i.setType("vnd.android-dir/mms-sms");
				startActivity(i);
				
			}
		});
		
	}
	public void smsSend(String phoneNumber,String sms) {
		SmsManager smsManger= SmsManager.getDefault();
		smsManger.sendTextMessage(phoneNumber, null, sms, null, null);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
