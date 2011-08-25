package com.diaspark.gif;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class NotifyHelper extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main2);
	Intent myData= getIntent();
	// extract the extra-data in the Notification
	String msg= myData.getStringExtra("extendedTitle") + "\n"
	+ myData.getStringExtra("extendedText");
	Toast.makeText(getApplicationContext(), msg, 1).show();
	}
}
	
