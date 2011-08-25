package com.diaspark.gif;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GenerateAlarm extends Activity {

    Toast mToast;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.alarmdemo);
        Button button = (Button) findViewById(R.id.set_alarm_button);
        button.setOnClickListener(this.mOneShotListener);
    }

    private OnClickListener mOneShotListener = new OnClickListener() {

        public void onClick(View v) {

        	setOneTimeAlarm();
          /*  Intent intent = new Intent(GenerateAlarm.this, AlarmReceiver.class);

            PendingIntent appIntent = PendingIntent.getBroadcast(GenerateAlarm.this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 50);

            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), appIntent);*/

          
        }
    };
    
    public void setOneTimeAlarm() {
    	
    	  Intent intent = new Intent(this, AlarmReceiver.class);
    	  
    	  PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
    	    intent, PendingIntent.FLAG_ONE_SHOT);
    	  AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    	  am.set(AlarmManager.RTC_WAKEUP,
    	    System.currentTimeMillis() + (5 * 1000), pendingIntent);
    	 }

    	 public void setRepeatingAlarm() {
    	  Intent intent = new Intent(this, AlarmReceiver.class);
    	  PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
    	    intent, PendingIntent.FLAG_CANCEL_CURRENT);
    	  AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    	  am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
    	    (5 * 1000), pendingIntent);
    	 }

}
