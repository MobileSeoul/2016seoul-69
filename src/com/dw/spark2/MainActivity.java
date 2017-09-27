package com.dw.spark2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	FrameLayout controlLayout;
	ImageView control2;
	ControlView controlView;
	private Typeface mface;
	private FrameLayout frameLayout;
	private SurfaceView surfaceView;	  
	private SurfaceHolder surfaceHolder;
	private Camera camera;
	private static int r1=0, g1=0, b1=0;
	double z1=0;
	private static int r4=262143, g4=262143, b4=262143;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		surfaceView =  (SurfaceView) findViewById(R.id.surfaceView1);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(surfaceListener);
		control2 = (ImageView)findViewById(R.id.control2);
		control2.setRotation(90);
		control2.setScaleX((float) 1.3);
		control2.setScaleY((float) 1.3);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		controlView = new ControlView(MainActivity.this,mHandler,metrics.widthPixels); 
		controlLayout = (FrameLayout)findViewById(R.id.control);
		controlLayout.addView(controlView);
	}

	@Override
	public synchronized void onPause() {
		super.onPause();
	//	if(camera!=null){
		camera.stopPreview();
			camera.release();
			camera = null;
		//}
	}
	@Override
	public synchronized void onResume() {
		super.onResume();
	}
	static public void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {
		final int frameSize = width * height;

		for (int j = 0, yp = 0; j < height; j++) {
			int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
			for (int i = 0; i < width; i++, yp++) {
				int y = (0xff & ((int) yuv420sp[yp])) - 16;
				if (y < 0) y = 0;
				if ((i & 1) == 0) {
					v = (0xff & yuv420sp[uvp++]) - 128;
					u = (0xff & yuv420sp[uvp++]) - 128;
				}

				int y1192 = 1192 * y;
				int r = (y1192 + 1634 * v);
				int g = (y1192 - 833 * v - 400 * u);
				int b = (y1192 + 2066 * u);

				if (r < r1) r = r1; else if (r > r4) r = r4;
				if (g < g1) g = g1; else if (g > g4) g = g4;
				if (b < b1) b = b1; else if (b > b4) b = b4;

				rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
			}
		}
	}

	private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			int[] a;
			a = new int[640*480];
			decodeYUV420SP(a,data,640,480);
			Bitmap bitmap1 = Bitmap.createBitmap(640, 480, Bitmap.Config.ARGB_8888);
			bitmap1.setPixels(a, 0, 640, 0, 0, 640, 480);
			if(control2 !=null)control2.setImageBitmap(bitmap1);
		}
	};

	private SurfaceHolder.Callback surfaceListener = new SurfaceHolder.Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			camera.release();
			camera = null;
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			camera = Camera.open();
			try {
				camera.setPreviewDisplay(holder);
				camera.setPreviewCallback(previewCallback);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
			// TODO Auto-generated method stub
			Parameters param = camera.getParameters();
			param.setPreviewSize(640,480);
			camera.setParameters(param);
			Log.e("TAG", String.valueOf(param.getSupportedPreviewSizes()));
			camera.startPreview();

		}
	};

	@SuppressLint({ "HandlerLeak", "RtlHardcoded" }) 
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 11:
				r1 = 262143/msg.arg2*msg.arg1;
				Log.e("AGG", String.valueOf(r1*255/262143));
				break;
			case 12:
				r4 = 262143- 262143/msg.arg2*msg.arg1;
				break;
			case 21:
				g1 = 262143/msg.arg2*msg.arg1;
				break;
			case 22:
				g4 = 262143- 262143/msg.arg2*msg.arg1;
				break;
			case 31:
				b1 = 262143/msg.arg2*msg.arg1;
				break;
			case 32:
				b4 = 262143- 262143/msg.arg2*msg.arg1;
				break;
			case 41:
				z1 = (200*msg.arg1)/msg.arg2;
				z1 = 1.3+z1*0.01;

				control2.setScaleX((float) z1);
				control2.setScaleY((float) z1);
				break;
			}
		}
	};
}
