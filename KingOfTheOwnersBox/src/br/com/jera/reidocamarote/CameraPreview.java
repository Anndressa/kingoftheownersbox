package br.com.jera.reidocamarote;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private AutoFocusCallback autofocusCallback;

	@SuppressWarnings("deprecation")
	public CameraPreview(Context context, AutoFocusCallback autofocusCallback) {
		super(context);

		this.autofocusCallback = autofocusCallback;

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
		// deprecated setting, but required on Android versions prior to 3.0
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.

		if (mHolder.getSurface() == null) {
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
		}

		// set preview size and make any resize, rotate or
		// reformatting changes here

		// start preview with new settings
		setCameraDisplay(mHolder);
		if(mCamera!=null){
			mCamera.startPreview();
			mCamera.autoFocus(autofocusCallback);
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		setCameraDisplay(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mCamera != null) {
			mCamera.cancelAutoFocus();
			mCamera.stopPreview();
		}
	}

	public void setCamera(Camera mCamera) {
		this.mCamera = mCamera;
		
		 if (mCamera != null) {
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
                     if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
                             mCamera.setDisplayOrientation(90);
                     else
                             mCamera.setDisplayOrientation(0);
			requestLayout();
		}
	}

	public void setCameraDisplay(SurfaceHolder holder) {
		try {
			mCamera.setPreviewDisplay(holder);
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("PIC_SHARE_SPORTS",
					"Error setting camera preview: " + e.getMessage());
		}
	}

}
