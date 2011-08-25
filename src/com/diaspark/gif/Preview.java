/*
============================================================================
Name        : Preview.java
Author      : Ila Sambharia
Date		: 28-April-2011
Version     :
Copyright   : Your copyright notice
Description : Responsible for camera settings.
============================================================================
 */
package com.diaspark.gif;

import java.io.IOException;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Preview extends SurfaceView implements SurfaceHolder.Callback {
	SurfaceHolder mHolder;
	Camera mCamera;

	Preview(Context context) {
		super(context);
		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, acquire the camera and tell it where
		// to draw.
		mCamera = Camera.open();
		try {
			mCamera.setPreviewDisplay(holder);
		} catch (IOException exception) {
			mCamera.release();
			mCamera = null;
			// TODO: add more exception handling logic here
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Surface will be destroyed when we return, so stop the preview.
		// Because the CameraDevice object is not a shared resource, it's very
		// important to release it when the activity is paused.
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		
		Camera.Parameters parameters = mCamera.getParameters();
		
		// You need to choose the most appropriate previewSize for your app
		// Camera.Size previewSize = alist.get(0);// .... select one of
		// previewSizes here
		// parameters.setPreviewSize(previewSize.width, previewSize.height);
		//parameters.setPictureSize(100, 100);
		//parameters.setPreviewSize(w, h);
		parameters.setPictureFormat(PixelFormat.JPEG);
		mCamera.setParameters(parameters);
		mCamera.startPreview();
	}

}
