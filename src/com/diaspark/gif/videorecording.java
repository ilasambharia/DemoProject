package com.diaspark.gif;

import java.io.File;
import java.io.IOException;



import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class videorecording extends Activity implements OnClickListener, SurfaceHolder.Callback{
	Preview preview;
    MediaRecorder recorder;
    SurfaceHolder holder;
    FrameLayout pCameraLayout;
    boolean recording=false;
    Button pBtnCapture;
    public static final String TAG = "VIDEOCAPTURE";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recorder = new MediaRecorder();// Instantiate our media recording object
        //initRecorder();
        setContentView(R.layout.addfoodbyimage);
        
       // preview = new Preview(this);


		pBtnCapture = (Button) findViewById(R.id.idBtnCapture);
		pBtnCapture.setOnClickListener(this);

        SurfaceView cameraView = (SurfaceView) findViewById(R.id.surface_view);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
       

        cameraView.setClickable(true);// make the surface view clickable
        cameraView.setOnClickListener((OnClickListener) this);/// onClicklistener to be called when the surface view is clicked

    }

   /* private void initRecorder() {// this takes care of all the mediarecorder settings
        File OutputFile = new File(Environment.getExternalStorageDirectory().getPath());
        String video= "/DCIM/100MEDIA/Video";
        CamcorderProfile cpHigh = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
         recorder.setProfile(cpHigh);        

        //recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        // default microphone to be used for audio
       // recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);// default camera to be used for video capture.
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);// generally used also includes h264 and best for flash
       // recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264); //well known video codec used by many including for flash
        //recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);// typically amr_nb is the only codec for mobile phones so...

        //recorder.setVideoFrameRate(15);// typically 12-15 best for normal use. For 1080p usually 30fms is used.
       // recorder.setVideoSize(720,480);// best size for resolution.
        //recorder.setMaxFileSize(10000000);
        recorder.setOutputFile(OutputFile.getAbsolutePath()+video+".3gp");
        //recorder.setAudioEncodingBitRate(8000);
       recorder.setMaxDuration(600000);


    }*/
    
    private void initRecorder() {
    	recorder.reset();
    	// CamcorderProfile cpHigh = CamcorderProfile
       //  .get(CamcorderProfile.QUALITY_HIGH);
    	 //recorder.setProfile(cpHigh);
    	recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile("/sdcard/videocapture_example.mp4");
        //recorder.setMaxDuration(50000); // 50 seconds
        //recorder.setMaxFileSize(5000000); // Approximately 5 megabytes
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        
        

    }

    /*if(record.setMaxDuration>60000){

        recorder.stop();
        MediaRecorder.OnInfoListener;
        Toast display = Toast.makeText(this, "You have exceeded the record time", Toast.LENGTH_SHORT);// toast shows a display of little sorts
        display.show();
        return true;
    }*/

    private void prepareRecorder() {
        recorder.setPreviewDisplay(holder.getSurface());

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onClick(View v) {
        if (recording) {
            recorder.stop();
            recording = false;

            // Let's initRecorder so we can record again
            initRecorder();
            prepareRecorder();
            Toast display = Toast.makeText(this, "Stopped Recording", Toast.LENGTH_SHORT);// toast shows a display of little sorts
            display.show();


        } else {
        	// initRecorder();
            // prepareRecorder();
            recorder.start();
            Log.v(TAG,"Recording Started"); 
            recording = true;

        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
    	Log.v(TAG,"surfaceCreated");
        initRecorder();     
        prepareRecorder();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        finish();

    }
}