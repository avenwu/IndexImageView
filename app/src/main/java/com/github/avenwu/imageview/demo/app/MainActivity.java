package com.github.avenwu.imageview.demo.app;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.github.avenwu.imageview.IndexImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final IndexImageView imageView = (IndexImageView)findViewById(R.id.iv_test);
		imageView.postDelayed(new Runnable() {
			@Override
			public void run() {
//				imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.image2));
				imageView.setImageDrawable(getResources().getDrawable(R.drawable.image2));
			}
		}, 6*1000);
	}
}
