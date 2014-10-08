package com.github.avenwu.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class RoundedDrawable extends Drawable {

	protected final float cornerRadius;
	protected final int margin;

	protected final RectF mRect = new RectF(), mBitmapRect;
	protected final BitmapShader bitmapShader;
	protected final Paint paint;

	public RoundedDrawable(Bitmap bitmap, int cornerRadius, int margin) {
		this.cornerRadius = cornerRadius;
		this.margin = margin;
		bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
		mBitmapRect = new RectF(margin, margin, bitmap.getWidth() - margin, bitmap.getHeight() - margin);
		mRect.set(mBitmapRect.left, mBitmapRect.top, mBitmapRect.right, mBitmapRect.bottom);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(bitmapShader);
	}

	@Override
	public void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);
		mRect.set(margin, margin, bounds.width() - margin, bounds.height() - margin);
		// Resize the original bitmap to fit the new bound
		Matrix shaderMatrix = new Matrix();
		shaderMatrix.setRectToRect(mBitmapRect, mRect, Matrix.ScaleToFit.FILL);
		bitmapShader.setLocalMatrix(shaderMatrix);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	public void setAlpha(int alpha) {
		paint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		paint.setColorFilter(cf);
	}
}