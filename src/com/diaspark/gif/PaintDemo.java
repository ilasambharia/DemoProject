package com.diaspark.gif;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;



public class PaintDemo extends Activity{

    drawingView dview;                    //creating the reference
 
@Override
   public void onCreate(Bundle savedInstanceState)
   {
        super.onCreate(savedInstanceState);
      // dview=new drawingView(this); //creating the instance
      // dview.setBackgroundResource(R.drawable.shapedemo);
      // setContentView(dview);         //adding to the activity
      // settransition();
		setContentView(R.layout.expandcollapse);
        ImageView spaceshipImage = (ImageView) findViewById(R.id.imgview);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_animation);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        
       // readContact();
        
        List<String> pList = readSms();
        for(int i=0;i<pList.size();i++)
        {
        	Log.v("SMS", pList.get(i).toString());
        }
    }

	public void settransition()
	{
		setContentView(R.layout.expandcollapse);
		Resources res = getResources();
		TransitionDrawable transition = (TransitionDrawable) res.getDrawable(R.drawable.expandcollapse);
		ImageView image = (ImageView) findViewById(R.id.imgview);
		image.setImageDrawable(transition);
		transition.startTransition(10000);
	}
	
	public void readContact()
	{
		  ContentResolver cr = getContentResolver();
	        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
	                null, null, null, null);
	        if (cur.getCount() > 0) {
		    while (cur.moveToNext()) {
		    String id = cur.getString(
	                        cur.getColumnIndex(ContactsContract.Contacts._ID));
			String name = cur.getString(
	                      cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			Log.v("Name", name);
	 		if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
	 		    //Query phone here.  Covered next
	 	        }
	            }
	 	}
	}
	
	public List<String> readSms()
	{
		ContentResolver mContentResolver = getContentResolver();
		Uri mSmsQueryUri = Uri.parse("content://sms/inbox");
		List<String> messages = new ArrayList<String>();
		Cursor cursor = null;
		try {
			cursor = mContentResolver.query(mSmsQueryUri, null, null, null,
					null);
			if (cursor == null) {
				return messages;
			}

			for (boolean hasData = cursor.moveToFirst(); hasData; hasData = cursor
					.moveToNext()) {
				final String body = cursor.getString(cursor
						.getColumnIndexOrThrow("body"));
				messages.add(body);
			}
		} catch (Exception e) {
		} finally {
			cursor.close();
		}
		return messages;
		    
	}
}
