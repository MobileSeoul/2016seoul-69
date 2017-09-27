package com.dw.spark2;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class IntroActivity extends Activity {

	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	Handler h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		h = new Handler();
		h.postDelayed(mrun, 1500);
	}

	Runnable mrun = new Runnable() {
		public void run() {
			Intent start = new Intent(IntroActivity.this, MainActivity.class);
			startActivity(start);
			finish();
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}
	};

}

