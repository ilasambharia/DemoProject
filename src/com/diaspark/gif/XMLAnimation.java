package com.diaspark.gif;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class XMLAnimation extends Activity 
{
	MediaRecorder recorder;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
      
        

        recorder = new MediaRecorder();
		//
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
		recorder.setMaxDuration((int) 50000); 
		//recorder.setOnInfoListener(m_BeMeSelf);
		recorder.setVideoSize(320, 240); 
		recorder.setVideoFrameRate(15); 
		CPreview mPreview = new CPreview(this,recorder);
		setContentView(mPreview);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  setContentView(R.layout.simpleanim);
	        
//	     // Hook into the object to be animated.
//	       TextView animWindow = (TextView)findViewById(0x7f040000);
//
//	        // Load the animation from XML (XML file is res/anim/move_animation.xml).
//	        Animation anim = AnimationUtils.loadAnimation(XMLAnimation.this, R.drawable.simple_animation);
//	        anim.setRepeatMode(Animation.NO_REPEAT);
//
//	        // Play the animation.
//	        animWindow.startAnimation(anim);
	        
	     // Load the ImageView that will host the animation and
	        // set its background to our AnimationDrawable XML resource.
	        
	        ImageView img = (ImageView)findViewById(R.id.simple_anim);
	        img.setBackgroundResource(R.anim.simple_animation);

	        
	        MyAnimationRoutine mar = new MyAnimationRoutine();
	        MyAnimationRoutine2 mar2 = new MyAnimationRoutine2();
	        
	        Timer t = new Timer(false);
	        t.schedule(mar, 100);
	        Timer t2 = new Timer(false);
	        t2.schedule(mar2, 5000);
    }

    class CPreview extends SurfaceView implements SurfaceHolder.Callback
	{
		//Create objects for MediaRecorder and SurfaceHolder.
		SurfaceHolder mHolder;
		MediaRecorder tempRecorder;

		//Create constructor of Preview Class. In this, get an object of 
	    //surfaceHolder class by calling getHolder() method. After that add   
	    //callback to the surfaceHolder. The callback will inform when surface is 
	    //created/changed/destroyed. Also set surface not to have its own buffers.
		public CPreview(Context context,MediaRecorder recorder) {
			super(context);
			tempRecorder=recorder;
			mHolder=getHolder();
			mHolder.addCallback(this);
			
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			// TODO Auto-generated constructor stub
		}

		public Surface getSurface()
		{
			return mHolder.getSurface();
		}
		
		// Implement the methods of SurfaceHolder.Callback interface

	    // SurfaceCreated : This method gets called when surface is created.
	    // In this, initialize all parameters of MediaRecorder object.
		//The output file will be stored in SD Card.
		
		public void surfaceCreated(SurfaceHolder holder){
			
			tempRecorder.setOutputFile("/sdcard/videooutput.3gpp");

			tempRecorder.setPreviewDisplay(getSurface());
			try{
				tempRecorder.prepare();
				tempRecorder.start();

			} catch (Exception e) {
				String message = e.getMessage();
				tempRecorder.release();
				tempRecorder = null;
			}
			
			
		}

		public void surfaceDestroyed(SurfaceHolder holder) 
		{
			if(tempRecorder!=null)
			{
				tempRecorder.release();
				tempRecorder = null;
			}
			
		}
	
		
	    public void surfaceChanged(SurfaceHolder holder, int format, int width,
	            int height) {
	    	
	    }
	}
    class MyAnimationRoutine extends TimerTask
    {
    	MyAnimationRoutine()
    	{
    	}
    	
    	
    	public void run()
    	{
        	ImageView img = (ImageView)findViewById(R.id.simple_anim);
            // Get the background, which has been compiled to an AnimationDrawable object.
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

            // Start the animation (looped playback by default).
            frameAnimation.start();
    	}
    }


    class MyAnimationRoutine2 extends TimerTask
    {
    	MyAnimationRoutine2()
    	{
    	}
    	
    	
    	public void run()
    	{
        	ImageView img = (ImageView)findViewById(R.id.simple_anim);
            // Get the background, which has been compiled to an AnimationDrawable object.
            AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
            
            // stop the animation (looped playback by default).
            frameAnimation.stop();
    	}
    }





}


