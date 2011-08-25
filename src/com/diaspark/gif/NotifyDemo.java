package com.diaspark.gif;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotifyDemo extends Activity {
	Button btnGo;
	Button btnStop;
	int notificationId = 1;
	NotificationManager notificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificationlayout);
		btnGo = (Button) findViewById(R.id.btnGo);
		btnGo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// define a notification manager
				String serName = Context.NOTIFICATION_SERVICE;
				notificationManager = (NotificationManager) getSystemService(serName);
				// define notification using: icon, text, and timing.
				int icon = R.drawable.ic_launcher_email;
				String tickerText = "1. My Notification TickerText";
				long when = System.currentTimeMillis();
				Notification notification = new Notification(icon, tickerText,when);
				// configure appearance of the notification
				String extendedTitle = "2. My Extended Title";
				String extendedText = "3. This is an extended and very important message";
				// set a Pending Activity to take care of the potential request
				// the user
				// may have by clicking on the notification asking for more
				// explanations
				Intent intent = new Intent(getApplicationContext(),
						NotifyHelper.class);
				intent.putExtra("extendedText", extendedText);
				intent.putExtra("extendedTitle", extendedTitle);
				PendingIntent launchIntent = PendingIntent.getActivity(
						getApplicationContext(), 0, intent, 0);
				notification.setLatestEventInfo(getApplicationContext(),
						extendedTitle, extendedText, launchIntent);
				// trigger notification
				notificationId = 1;
				notificationManager.notify(notificationId, notification);
			}// click
		});
		// ///////////////////////////////////////////////////////////////////////////
		btnStop = (Button) findViewById(R.id.btnStop);
		btnStop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// canceling a notification
				notificationId = 1;
				notificationManager.cancel(notificationId);
			}
		});
	}// onCreate
}// NotifyDemo1