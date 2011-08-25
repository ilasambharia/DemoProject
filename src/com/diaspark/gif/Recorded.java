package com.diaspark.gif;


import java.io.IOException;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Recorded extends Activity {
	Button btnGo;
	Button btnStop;
	MediaRecorder mRecorder;
	MediaPlayer mPlayer = null;

	String mFileName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificationlayout);
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mFileName += "/audiorecordtest.wav";
		
		btnGo = (Button) findViewById(R.id.btnGo);
		btnGo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// define a notification manager
				startRecording();
				
			//	startPlaying();
				
			}// click
		});
		// ///////////////////////////////////////////////////////////////////////////
		btnStop = (Button) findViewById(R.id.btnStop);
		btnStop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				stopRecording();
				
				
				//stopPlaying();
			}
		});
	}// onCreate
	
	  private void startRecording() {
	        mRecorder = new MediaRecorder();
	        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	        mRecorder.setOutputFile(mFileName);
	        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

	        try {
	            mRecorder.prepare();
	        } catch (IOException e) {
	            Log.e("catch", "prepare() failed");
	        }

	        mRecorder.start();
	    }

	    private void stopRecording() {
	    	if(mRecorder!=null)
	    	{
	    		mRecorder.stop();
	    		mRecorder.release();
	    		mRecorder = null;
	    	}
	    }
	    
	    private void startPlaying() {
	        mPlayer = new MediaPlayer();
	        try {
	            mPlayer.setDataSource(mFileName);
	            mPlayer.prepare();
	            mPlayer.start();
	        } catch (IOException e) {
	            Log.e("catch", "prepare() failed");
	        }
	    }

	    private void stopPlaying() {
	    	if(mPlayer!=null)
	    	{
	    		mPlayer.stop();
	    		mPlayer.release();
	    		mPlayer = null;
	    	}
	    }


}// NotifyDemo1