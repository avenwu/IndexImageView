package com.github.avenwu.imageview.demo.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
	static {
		System.loadLibrary("hello");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String text = stringFromJNI();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		Log.d("TEST", text);
	}

	public native String stringFromJNI();
}
