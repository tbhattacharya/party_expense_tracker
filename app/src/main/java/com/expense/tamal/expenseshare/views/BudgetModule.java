package com.expense.tamal.expenseshare.views;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.expense.tamal.expenseshare.R;


public class BudgetModule extends RelativeLayout {

    private static final String TAG = BudgetModule.class.getSimpleName();

    private BudgetProgressCircle budgetProgressCircle;

    private TextView mAdviseView;


    private ObjectAnimator mProgressBarAnimator;

    private int mExecClubTierPoints;

    // total threshold points
    private int mExecClubTotalPoints;

    private float mTierPointProgress;

    // for circle point value
    int tierpointProgressedNumber;


    private boolean mIsCircleAnimationShown;

    private boolean mIsPodAnimationShown;


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewTreeObserver vto = getViewTreeObserver();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewTreeObserver vto = getViewTreeObserver();
    }

    public BudgetModule(Context context) {
        super(context);
        init();
    }

    public BudgetModule(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BudgetModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.budget_module, this);
        mAdviseView = (TextView) findViewById(R.id.budget_advice);
        budgetProgressCircle = (BudgetProgressCircle) findViewById(R.id.budget_circle);
        budgetProgressCircle.setMarkerEnabled(false);
        setupViews();
    }

    private void setupViews() {

        budgetProgressCircle.setProgressColor(getResources()
                .getColor(R.color.indicator_green));
        budgetProgressCircle.setProgressBackgroundColor(getResources()
                .getColor(R.color.white_opacity30));
        budgetProgressCircle.setWheelSize(35);

    }

    public void setupBudgetView(int budget, int total) {

        resetAnimationShownFlags();
        mExecClubTierPoints = total;
        mExecClubTotalPoints = budget;

        if (mExecClubTotalPoints > 0) {
            mTierPointProgress = (float) mExecClubTierPoints / mExecClubTotalPoints;
        }

    }

    private void animateCircle(final float progress, final int duration) {

        mProgressBarAnimator = ObjectAnimator.ofFloat(
                budgetProgressCircle, "progress", progress);
        mProgressBarAnimator.setDuration(duration);
        mProgressBarAnimator.setInterpolator(new DecelerateInterpolator(0.8f));
        mProgressBarAnimator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAdviseView.setText("Test for now!");

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        });
        mProgressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {

                tierpointProgressedNumber = (int) ((float) ((Float) animation
                        .getAnimatedValue()) * mExecClubTotalPoints);
                budgetProgressCircle.setProgress((Float) animation
                        .getAnimatedValue());

            }
        });

        budgetProgressCircle.setMarkerProgress(progress);
        mProgressBarAnimator.start();

    }

    /*@Override
    public void onScrollChanged() {
        startAnimationIfOnScreen(this);
    }*/

    public void startAnimationIfOnScreenIfRequired(Rect visibleRect,
                                                   Rect tierPointModuleBounds) {
        boolean isCircleAnimationRequired = false;


        if (!mIsCircleAnimationShown) {
            int circleAnimationLimit = tierPointModuleBounds.bottom
                    - (tierPointModuleBounds.height()) / 3;

            isCircleAnimationRequired = Rect.intersects(visibleRect,
                    tierPointModuleBounds)
                    && visibleRect.bottom > circleAnimationLimit;

            //if (isCircleAnimationRequired) {

                if (mTierPointProgress > 1) {
                    animateCircle(mTierPointProgress, 2500);
                } else {
                    animateCircle(mTierPointProgress, 1500);
                }

                mIsCircleAnimationShown = true;
            //}
        }

    }

    public boolean isAnimationShown() {
        return mIsPodAnimationShown;
    }

    private void resetAnimationShownFlags() {
        mIsPodAnimationShown = false;
        mIsCircleAnimationShown = false;
    }
}
