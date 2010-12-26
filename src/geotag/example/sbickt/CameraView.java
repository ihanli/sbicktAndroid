package geotag.example.sbickt;

import geotag.core.R;
import geotag.core.R.id;
import geotag.core.R.layout;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CameraView extends Activity implements SurfaceHolder.Callback {
	
	private Camera mCamera;
	private SurfaceHolder mSurfaceHolder;
	private SurfaceView mSurfaceView;
	private boolean mPreviewRunning;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.camera_view);
		mSurfaceView = (SurfaceView) findViewById(R.id.surface);
		
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if(mPreviewRunning)
			mCamera.stopPreview();
		
		Camera.Parameters p = mCamera.getParameters();
		//p.setPreviewSize(width, height);
		mCamera.setParameters(p);
		
		try {
			mCamera.setPreviewDisplay(mSurfaceHolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mCamera.startPreview();
		mPreviewRunning = true;
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		mCamera.stopPreview();
		mPreviewRunning = false;
		mCamera.release();
		
	}
	
}
