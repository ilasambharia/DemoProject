/**
============================================================================
Name         : SMSListener.java
Modified     : Ila Sambharia
History		 : 
23-Nov-2010  : 
	1) SMSListener created for Issue AB-46.
24-Nov-2010	 :
	1) Fetched the required data from Preference for Issue AB-46.
	2) Called the gettransactionstatus, and if it returns SUCCESS
	   then Status in preference is updated.
============================================================================
 **/
package com.diaspark.gif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver  {
	static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	String shortCode = null;
	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		// Note: long sms are broken and transmitted into various pieces
		String msg = "";
		int smsPieces = messages.length;
		for (int n = 0; n < smsPieces; n++) {
		smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		// grab all pieces of the intercepted sms
		msg += "\n" + (n + 1) + " -of- " + smsPieces + "\n"
		+ "Sender:\t" + smsMessage[n].getOriginatingAddress() + "\n"
		+ "Body: \n " + smsMessage[n].getMessageBody();
		}
		
		// show first part of intercepted (current) message
		Toast toast = Toast.makeText(context, "Received SMS: "
		+ msg, Toast.LENGTH_LONG);
		toast.show();
	
	}

}
