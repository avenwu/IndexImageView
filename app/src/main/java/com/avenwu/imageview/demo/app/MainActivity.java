package com.avenwu.imageview.demo.app;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.avenwu.imageview.IndexImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IndexImageView imageView = new IndexImageView(this);
		imageView.setImageResource(R.color.grey);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(200, 200);
		imageView.setLayoutParams(layoutParams);
		addContentView(imageView, layoutParams);
	}
}
