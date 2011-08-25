package com.diaspark.gif;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentDemo extends Activity {

	boolean bIsStart =false;
	ComponentName service;
	Intent intentMyService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intentdemo);
		final Button button = (Button)findViewById(R.id.btnStart);
		button.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				bIsStart = !bIsStart;
				if(bIsStart)
				{
					startMyService();
				}
				else
				{
					if(service!=null)
					{
						 stopService(intentMyService);
					}
				}
				}
			
		}
		
		);
	}
	

	public void startMyService()
	{
		intentMyService= new Intent(this, MyService1.class);
		service = startService(intentMyService);
	}
}
