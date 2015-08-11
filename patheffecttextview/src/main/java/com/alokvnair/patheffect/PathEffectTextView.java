package com.alokvnair.patheffect;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class PathEffectTextView extends View {

    private static final float BASE_SQUARE_UNIT = 72f;
    private static final int DEFAULT_COLOR = Color.rgb(0, 0, 0);
    private String mText = "";
    private float mPhase;
    private TYPE mType = TYPE.SINGLE;
    private float mScaleFactor = 1.0f;
    private ArrayList<float[]> mDatas;
    private ArrayList<Path> mPaths = new ArrayList<>();
    private Paint mPaint = new Paint();
    private final Object mSvgLock = new Object();

    public enum TYPE {
        SINGLE, MULTIPLE
    }

    public PathEffectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PathEffectTextView,
                0, 0);
        mPaint.setColor(attributes.getColor(R.styleable.PathEffectTextView_text_color, DEFAULT_COLOR));
        mPaint.setStrokeWidth(attributes.getFloat(R.styleable.PathEffectTextView_stroke_width, 2));
        mPaint.setStyle(Paint.Style.STROKE);
        boolean isMultiple = attributes.getBoolean(R.styleable.PathEffectTextView_multiple, false);

        if (isMultiple)
            mType = TYPE.MULTIPLE;
        else
            mType = TYPE.SINGLE;

        attributes.recycle();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));

    }

    public void init(String text) {
        if (text == null || text.length() == 0)
            return;

        requestLayout();
        invalidate();

        mText = text;
        mDatas = MatchPath.getPath(mText);
        ObjectAnimator mSvgAnimator = ObjectAnimator.ofFloat(this, "phase", 0.0f, 1.0f).setDuration(3000);
        mSvgAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaths == null)
            return;
        synchronized (mSvgLock) {
            for (int i = 0; i < mPaths.size(); i++)
                canvas.drawPath(mPaths.get(i), mPaint);
        }
    }


    private void updatePathsPhaseLocked() {
        mPaths.clear();
        float singleFactor = mPhase * mDatas.size();
        for (int i = 0; i < mDatas.size(); i++) {
            Path path = new Path();
            path.moveTo(mDatas.get(i)[0], mDatas.get(i)[1]);
            path.lineTo(mDatas.get(i)[2], mDatas.get(i)[3]);

            if (mType == TYPE.MULTIPLE) {
                PathMeasure measure = new PathMeasure(path, false);
                Path dst = new Path();
                measure.getSegment(0.0f, mPhase * measure.getLength(), dst, true);
                mPaths.add(dst);
            } else {
                if (singleFactor - (i + 1) >= -0.01)
                    mPaths.add(path);
                else if (i - Math.floor(singleFactor) < 0.0001) {
                    Path dst = new Path();
                    PathMeasure measure = new PathMeasure(path, false);
                    measure.getSegment(0.0f, (singleFactor % 1) * measure.getLength(), dst, true);
                    mPaths.add(dst);
                }
            }

        }
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (mText.length() * BASE_SQUARE_UNIT * mScaleFactor + getPaddingLeft()
                    + getPaddingRight());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (BASE_SQUARE_UNIT * mScaleFactor) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
