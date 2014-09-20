package com.avenwu.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Circle imageView with top-left index corner available
 */
public class IndexImageView extends ImageView {
    private final String TAG = IndexImageView.class.getSimpleName();
    private String mText = "1";
    private int mTextColor = Color.WHITE;
    private float mTextSize = 12;
    private TextPaint mTextPaint;
    private int mCircleColor = Color.WHITE;
    private int mTextBackground = Color.BLUE;
    private int mStrokeWidth = 2;
    private int mStrokeColor = Color.WHITE;
    private Paint mPolygonPaint;
    private Path mPath;
    private int[] x;
    private int[] y;
    Paint mCirclePaint;
    Paint mStrokePaint;
    Paint mGradientPaint;
    Shader mGradientShader;
    private boolean isIndexEnable = true;
    private boolean isGradientEnable = true;
    private int[] mGradientColorArray;

    public IndexImageView(Context context) {
        super(context);
    }

    public IndexImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.indexImageView);
    }

    public IndexImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.IndexImageView, defStyle, R.style.IndexImageViewStyle);
        mText = a.getString(R.styleable.IndexImageView_indexText);
        mTextColor = a.getColor(R.styleable.IndexImageView_indexFontColor, mTextColor);
        mTextSize = a.getDimension(R.styleable.IndexImageView_indexFontSize, mTextSize);
        mCircleColor = a.getColor(R.styleable.IndexImageView_circleBackground, mCircleColor);
        mTextBackground = a.getColor(R.styleable.IndexImageView_indexBackground, mTextBackground);
        isIndexEnable = a.getBoolean(R.styleable.IndexImageView_indexEnable, isIndexEnable);
        isGradientEnable = a.getBoolean(R.styleable.IndexImageView_gradientEnable, isGradientEnable);
        //avoid to crash editor preview
        if (isInEditMode()) {
            mGradientColorArray = new int[]{Color.WHITE, Color.YELLOW};
        } else {
            mGradientColorArray = getResources().getIntArray(a.getResourceId(R.styleable.IndexImageView_gradientColorArray, -1));
        }
        mStrokeColor = a.getColor(R.styleable.IndexImageView_strokeColor, mStrokeColor);
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.IndexImageView_strokeWidth, mStrokeWidth);
        a.recycle();
        mCirclePaint = new Paint();
        mCirclePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setFilterBitmap(true);
        mCirclePaint.setColor(Color.WHITE);

        mGradientPaint = new Paint();
        mGradientPaint.setAntiAlias(true);

        mStrokePaint = new Paint();
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(mStrokeColor);

        mPolygonPaint = new Paint();
        mPolygonPaint.setColor(mTextBackground);
        mPolygonPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPolygonPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float h = getMeasuredHeight();
        float w = getMeasuredWidth();
        if (isGradientEnable) {
            if (mGradientShader == null) {
                mGradientShader = new RadialGradient(w / 2.0f, h / 2.0f,
                        (float) Math.min(w / 2.0f, (h / 2.0)),
                        mGradientColorArray,
                        null, Shader.TileMode.REPEAT);
                mGradientPaint.setShader(mGradientShader);
            }
            canvas.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f) - ((mStrokeWidth - 0.5f) / 2.0f), mGradientPaint);
        }
        canvas.drawCircle(w / 2.0f, h / 2.0f, Math.min(w / 2.0f, h / 2.0f) - ((mStrokeWidth - 0.5f) / 2.0f), mStrokePaint);
        mPath.reset();
        if (isIndexEnable) {
            if (x == null) {
                x = new int[]{0, getWidth() / 2, 0};
                y = new int[]{0, 0, getHeight() / 2};
            }
            mPath.moveTo(x[0], y[0]);
            mPath.lineTo(x[1], y[1]);
            mPath.lineTo(x[2], y[2]);
            mPath.close();
            canvas.drawPath(mPath, mPolygonPaint);
            if (!TextUtils.isEmpty(mText)) {
                float[] width = new float[mText.length()];
                mTextPaint.getTextWidths(mText, width);
                float length = 0;
                for (float temp : width) {
                    length += temp;
                }
                mPath.reset();
                float diff = (float) Math.sqrt(length);
                mPath.moveTo(x[1] / 2 - diff, y[2] / 2 + diff);
                mPath.lineTo(x[1] / 2 + diff, y[2] / 2 - diff);
                mPath.close();
                canvas.drawPath(mPath, mPolygonPaint);
                canvas.drawTextOnPath(mText, mPath, 0, -2, mTextPaint);
            }
        }
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        isIndexEnable = true;
        invalidateTextPaintAndMeasurements();
    }

    public void setTextColor(int color) {
        mTextColor = color;
        invalidateTextPaintAndMeasurements();
    }

    public float getTextDimension() {
        return mTextSize;
    }

    public void setTextDimension(float size) {
        mTextSize = size;
        invalidateTextPaintAndMeasurements();
    }

    public int getTextBackground() {
        return mTextBackground;
    }

    public void setTextBackground(int id) {
        this.mTextBackground = getResources().getColor(id);
        mPolygonPaint.setColor(mTextBackground);
    }

    public void setIndexEnable(boolean enable) {
        isIndexEnable = enable;
        postInvalidate();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageDrawable(new RoundedDrawable(bm, getWidth() / 2, 0));
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (isInEditMode()) {
            super.setImageDrawable(drawable);
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            setImageBitmap(((BitmapDrawable) drawable).getBitmap());
        } else {
            super.setImageDrawable(drawable);
        }
    }

}
