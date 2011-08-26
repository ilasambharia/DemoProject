package com.diaspark.gif;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//check out change
public class AlarmReceiver extends BroadcastReceiver {
 @Override
    public void onReceive(Context context, Intent intent) {
    	NotificationManager nm = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
      CharSequence from = "Alarm TIme";
      CharSequence message = "Alarm Fired";
      PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
        new Intent(), 0);
      Notification notif = new Notification(R.drawable.icon,
        "Alarm fired", System.currentTimeMillis());
      notif.setLatestEventInfo(context, from, message, contentIntent);
      nm.notify(1, notif);
      
    }
    
   
}
