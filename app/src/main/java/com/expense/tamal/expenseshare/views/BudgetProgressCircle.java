package com.expense.tamal.expenseshare.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.expense.tamal.expenseshare.R;

/**
 * HoloCircularProgressBar custom view.
 * 
 * https://github.com/passsy/android-HoloCircularProgressBar
 * 
 * @author Pascal.Welsch
 * @version 1.3 (03.10.2014)
 * @since 05.03.2013
 * @Modified Tamal
 */
public class BudgetProgressCircle extends View {
    private Bitmap progressMark;

    /**
     * TAG constant for logging
     */
    private static final String TAG = BudgetProgressCircle.class
            .getSimpleName();

    /**
     * The rectangle enclosing the circle.
     */
    private final RectF mCircleBounds = new RectF();

    /**
     * the rect for the thumb square
     */
    private final RectF mSquareRect = new RectF();

    /**
     * the paint for the background.
     */
    private Paint mBackgroundColorPaint = new Paint();

    /**
     * the fading paint for plane icon
     */
    Paint mFadingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * The stroke width used to paint the circle.
     */
    private int mCircleStrokeWidth = 10;

    /**
     * The gravity of the view. Where should the Circle be drawn within the
     * given bounds
     *
     * {@link #computeInsets(int, int)}
     */
    private int mGravity = Gravity.CENTER;

    /**
     * The Horizontal inset calcualted in {@link #computeInsets(int, int)}
     * depends on {@link #mGravity}.
     */
    private int mHorizontalInset = 0;

    /**
     * true if not all properties are set. then the view isn't drawn and there
     * are no errors in the LayoutEditor
     */
    private boolean mIsInitializing = true;

    /**
     * flag if the marker should be visible
     */
    private boolean mIsMarkerEnabled = false;

    /**
     * indicates if the thumb is visible
     */
    private boolean mIsThumbEnabled = true;

    /**
     * The Marker color paint.
     */
    private Paint mMarkerColorPaint;

    /**
     * The Marker progress.
     */
    private float mMarkerProgress = 0.0f;

    /**
     * the overdraw is true if the progress is over 1.0.
     */
    private boolean mOverrdraw = false;

    /**
     * The current progress.
     */
    private float mProgress = 0.0f;

    /**
     * The color of the progress background.
     */
    private int mProgressBackgroundColor;

    /**
     * the color of the progress.
     */
    private int mProgressColor;

    /**
     * paint for the progress.
     */
    private Paint mProgressColorPaint;

    private int mThumbPadding;

    /**
     * Radius of the circle
     *
     * <p>
     * Note: (Re)calculated in {@link #onMeasure(int, int)}.
     * </p>
     */
    private float mRadius;

    /**
     * The Thumb color paint.
     */
    private Paint mThumbColorPaint = new Paint();

    /**
     * The Thumb pos x.
     *
     * Care. the position is not the position of the rotated thumb. The position
     * is only calculated in {@link #onMeasure(int, int)}
     */
    private float mThumbPosX;

    /**
     * The Thumb pos y.
     *
     * Care. the position is not the position of the rotated thumb. The position
     * is only calculated in {@link #onMeasure(int, int)}
     */
    private float mThumbPosY;

    /**
     * The pointer width (in pixels).
     */
    private int mThumbRadius = 20;

    /**
     * The Translation offset x which gives us the ability to use our own
     * coordinates system.
     */
    private float mTranslationOffsetX;

    /**
     * The Translation offset y which gives us the ability to use our own
     * coordinates system.
     */
    private float mTranslationOffsetY;

    /**
     * The Vertical inset calcualted in {@link #computeInsets(int, int)} depends
     * on {@link #mGravity}..
     */
    private int mVerticalInset = 0;

    private int mFaddingCount;

    /**
     * Instantiates a new holo circular progress bar.
     *
     * @param context
     *            the context
     */
    public BudgetProgressCircle(final Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new holo circular progress bar.
     *
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     */
    public BudgetProgressCircle(final Context context,
                                final AttributeSet attrs) {
        this(context, attrs, R.attr.budget_progress_attr);
    }

    /**
     * Instantiates a new holo circular progress bar.
     *
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     * @param defStyle
     *            the def style
     */
    public BudgetProgressCircle(final Context context,
                                final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        // load the styled attributes and set their properties
        final TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.budget_progress_attr, defStyle, 0);
        if (attributes != null) {
            try {
                setProgressColor(attributes.getColor(
                        R.styleable.budget_progress_attr_progress_color,
                        Color.CYAN));
                setProgressBackgroundColor(attributes
                        .getColor(
                                R.styleable.budget_progress_attr_progress_background_color,
                                Color.GREEN));
                setProgress(attributes.getFloat(
                        R.styleable.budget_progress_attr_progress, 0.0f));
                setMarkerProgress(attributes.getFloat(
                        R.styleable.budget_progress_attr_marker_progress,
                        0.0f));
                setWheelSize((int) attributes.getDimension(
                        R.styleable.budget_progress_attr_stroke_width, 10));
                setThumbEnabled(attributes.getBoolean(
                        R.styleable.budget_progress_attr_thumb_visible,
                        true));
                setMarkerEnabled(attributes.getBoolean(
                        R.styleable.budget_progress_attr_marker_visible,
                        true));

                mThumbPadding = getResources().getInteger(
                        R.integer.thumb_padding);
                mGravity = attributes.getInt(
                        R.styleable.budget_progress_attr_android_gravity,
                        Gravity.CENTER);
            } finally {
                // make sure recycle is always called.
                attributes.recycle();
            }
        }
        progressMark = BitmapFactory.decodeResource(getResources(),
                R.mipmap.budget_indicator);
        mThumbRadius = mCircleStrokeWidth * 2;

        updateBackgroundColor();

        updateMarkerColor();

        updateProgressColor();

        // the view has now all properties and can be drawn
        mIsInitializing = false;

    }

    /**
     * Compute insets.
     * 
     * <pre>
     *  ______________________
     * |_________dx/2_________|
     * |......| /'''''\|......|
     * |-dx/2-|| View ||-dx/2-|
     * |______| \_____/|______|
     * |________ dx/2_________|
     * </pre>
     * 
     * @param dx
     *            the dx the horizontal unfilled space
     * @param dy
     *            the dy the horizontal unfilled space
     */
    @SuppressLint("NewApi")
    private void computeInsets(final int dx, final int dy) {
        int absoluteGravity = mGravity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            absoluteGravity = Gravity.getAbsoluteGravity(mGravity,
                    getLayoutDirection());
        }

        switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
        case Gravity.LEFT:
            mHorizontalInset = 0;
            break;
        case Gravity.RIGHT:
            mHorizontalInset = dx;
            break;
        case Gravity.CENTER_HORIZONTAL:
        default:
            mHorizontalInset = dx / 2;
            break;
        }
        switch (absoluteGravity & Gravity.VERTICAL_GRAVITY_MASK) {
        case Gravity.TOP:
            mVerticalInset = 0;
            break;
        case Gravity.BOTTOM:
            mVerticalInset = dy;
            break;
        case Gravity.CENTER_VERTICAL:
        default:
            mVerticalInset = dy / 2;
            break;
        }
    }

    public int getCircleStrokeWidth() {
        return mCircleStrokeWidth;
    }

    /**
     * Gets the current rotation.
     * 
     * @return the current rotation
     */
    private float getCurrentRotation() {
        return 360 * mProgress;
    }

    /**
     * similar to {@link #getProgress}
     */
    public float getMarkerProgress() {
        return mMarkerProgress;
    }

    /**
     * Gets the marker rotation.
     * 
     * @return the marker rotation
     */
    private float getMarkerRotation() {
        return 360 * mMarkerProgress;
    }

    /**
     * gives the current progress of the ProgressBar. Value between 0..1 if you
     * set the progress to >1 you'll get progress % 1 as return value
     * 
     * @return the progress
     */
    public float getProgress() {
        return mProgress;
    }

    /**
     * Gets the progress color.
     * 
     * @return the progress color
     */
    public int getProgressColor() {
        return mProgressColor;
    }

    /**
     * @return true if the marker is visible
     */
    public boolean isMarkerEnabled() {
        return mIsMarkerEnabled;
    }

    /**
     * @return true if the marker is visible
     */
    public boolean isThumbEnabled() {
        return mIsThumbEnabled;
    }

    @Override
    protected void onDraw(final Canvas canvas) {

        // All of our positions are using our internal coordinate system.
        // Instead of translating
        // them we let Canvas do the work for us.
        canvas.translate(mTranslationOffsetX, mTranslationOffsetY);

        final float progressRotation = getCurrentRotation();

        // draw the background
        if (!mOverrdraw) {
            canvas.drawArc(mCircleBounds, 270, -(360 - progressRotation),
                    false, mBackgroundColorPaint);
        }

        // draw the progress or a full circle if overdraw is true
        canvas.drawArc(mCircleBounds, 270, mOverrdraw ? 360 : progressRotation,
                false, mProgressColorPaint);

        // draw plane image
        canvas.save();
        canvas.rotate(progressRotation - 90);
        canvas.rotate(90, mThumbPosX, mThumbPosY);

        if (mProgress == 0.0f) {
            canvas.drawBitmap(progressMark, mThumbPosX - 10, mThumbPosY
                    - mThumbPadding, null);
        } else {

            if (mFaddingCount > 255) {
                mFaddingCount = 255;
            }
            int alpha = 255 - mFaddingCount;
            mFadingPaint.setAlpha(alpha);
            canvas.drawBitmap(progressMark, mThumbPosX, mThumbPosY
                    - mThumbPadding, mFadingPaint);
            //  Log.d(TAG, "--p alpha= " + alpha);

        }

        canvas.restore();

    }

    @Override
    protected void onMeasure(final int widthMeasureSpec,
            final int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight()
                + getPaddingTop() + getPaddingBottom(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth()
                + getPaddingLeft() + getPaddingRight(), widthMeasureSpec);

        final int diameter;
        if (heightMeasureSpec == MeasureSpec.UNSPECIFIED) {
            // ScrollView
            diameter = width;
            computeInsets(0, 0);
        } else if (widthMeasureSpec == MeasureSpec.UNSPECIFIED) {
            // HorizontalScrollView
            diameter = height;
            computeInsets(0, 0);
        } else {
            // Default
            diameter = Math.min(width, height);
            computeInsets(width - diameter, height - diameter);
        }

        setMeasuredDimension(diameter, diameter);

        final float halfWidth = diameter * 0.5f;

        // width of the drawed circle (+ the drawedThumb)
        final float drawedWith;
        if (isThumbEnabled()) {
            drawedWith = mThumbRadius * (5f / 6f);
        } else if (isMarkerEnabled()) {
            drawedWith = mCircleStrokeWidth * 1.4f;
        } else {
            drawedWith = mCircleStrokeWidth / 2f;
        }

        // -0.5f for pixel perfect fit inside the viewbounds
        //mRadius = halfWidth - drawedWith - 0.5f;
        mRadius = halfWidth - drawedWith / 2;
        mCircleBounds.set(-mRadius + 10, -mRadius + 10, mRadius - 10,
                mRadius - 10);

        mThumbPosX = (float) (mRadius * Math.cos(0));
        mThumbPosY = (float) (mRadius * Math.sin(0));

        mTranslationOffsetX = halfWidth + mHorizontalInset;
        mTranslationOffsetY = halfWidth + mVerticalInset;

    }

    /**
     * Sets the marker enabled.
     * 
     * @param enabled
     *            the new marker enabled
     */
    public void setMarkerEnabled(final boolean enabled) {
        mIsMarkerEnabled = enabled;
    }

    /**
     * Sets the marker progress.
     * 
     * @param progress
     *            the new marker progress
     */
    public void setMarkerProgress(final float progress) {
        mIsMarkerEnabled = true;
        mMarkerProgress = progress;

    }

    /**
     * Sets the progress.
     * 
     * @param progress
     *            the new progress
     */
    public void setProgress(final float progress) {
        if (progress == mProgress) {
            return;
        }
        if (progress * 100 >= 70) {
            if ((int) ((float) (progress * 100)) > (int) ((float) (mProgress * 100))) {
                mFaddingCount += getResources().getInteger(
                        R.integer.thumb_alpha_interval);
            } else if ((int) ((float) (progress * 100)) < (int) ((float) (mProgress * 100))) {
                mFaddingCount -= getResources().getInteger(
                        R.integer.thumb_alpha_interval);
            }

        } else {
            mFaddingCount = 0;
        }

        if (progress == 1) {
            mOverrdraw = false;
            mProgress = 1;
        } else {

            if (progress >= 1) {
                mOverrdraw = true;
            } else {
                mOverrdraw = false;
            }

            mProgress = progress % 1.0f;
        }

        if (!mIsInitializing) {
            invalidate();
        }
    }

    /**
     * Sets the progress background color.
     * 
     * @param color
     *            the new progress background color
     */
    public void setProgressBackgroundColor(final int color) {
        mProgressBackgroundColor = color;

        updateMarkerColor();
        updateBackgroundColor();
    }

    /**
     * Sets the progress color.
     * 
     * @param color
     *            the new progress color
     */
    public void setProgressColor(final int color) {
        mProgressColor = color;

        updateProgressColor();
    }

    /**
     * shows or hides the thumb of the progress bar
     * 
     * @param enabled
     *            true to show the thumb
     */
    public void setThumbEnabled(final boolean enabled) {
        mIsThumbEnabled = enabled;
    }

    /**
     * Sets the wheel size.
     * 
     * @param dimension
     *            the new wheel size
     */
    public void setWheelSize(final int dimension) {
        mCircleStrokeWidth = dimension;

        // update the paints
        updateBackgroundColor();
        updateMarkerColor();
        updateProgressColor();
    }

    /**
     * updates the paint of the background
     */
    private void updateBackgroundColor() {
        mBackgroundColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundColorPaint.setColor(mProgressBackgroundColor);
        mBackgroundColorPaint.setStyle(Paint.Style.STROKE);
        mBackgroundColorPaint.setStrokeWidth(mCircleStrokeWidth);

        invalidate();
    }

    /**
     * updates the paint of the marker
     */
    private void updateMarkerColor() {
        mMarkerColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMarkerColorPaint.setColor(mProgressBackgroundColor);
        mMarkerColorPaint.setStyle(Paint.Style.STROKE);
        mMarkerColorPaint.setStrokeWidth(mCircleStrokeWidth / 2);

        invalidate();
    }

    /**
     * updates the paint of the progress and the thumb to give them a new visual
     * style
     */
    private void updateProgressColor() {
        updateProgressColor(mProgressColor);
    }

    public void updateProgressColor(int color) {
        mProgressColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressColorPaint.setColor(color);
        mProgressColorPaint.setStyle(Paint.Style.STROKE);
        mProgressColorPaint.setStrokeWidth(mCircleStrokeWidth);

        mThumbColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mThumbColorPaint.setColor(color);
        mThumbColorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mThumbColorPaint.setStrokeWidth(mCircleStrokeWidth);

        invalidate();
    }
}
