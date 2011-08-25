package com.diaspark.gif;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService1 extends Service {
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("<<MyService1-onStart>>", "I am alive-1!");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("<<MyService1-onStart>>", "I did something very quickly");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("<<MyService1-onDestroy>>", "I am dead-1");
	}
}