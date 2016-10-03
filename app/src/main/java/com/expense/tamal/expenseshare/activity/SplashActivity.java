package com.expense.tamal.expenseshare.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.expense.tamal.expenseshare.ExpenseShareApplication;
import com.expense.tamal.expenseshare.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final float ROTATE_FROM = 0.0f;

    private static final float ROTATE_TO = 360.0f;

    @BindView(R.id.viewFlipper)
    ViewFlipper mViewFlipper;

    @BindView(R.id.loading_spinner)
    ImageView mProgressImage;

    public static Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        context = this;
        startRotateAnimation(mProgressImage);
        startFlipping();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (((ExpenseShareApplication) getApplication()).isInitDone()) {
            launchHomeScreen();
        }
    }

    public static void launchHomeScreen() {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                context.startActivity(HomeActivity.getHomeActivityIntent(context));
                context.overridePendingTransition(R.anim.fade_in,
                        R.anim.fade_out);
                timer.cancel();
                context.finish();
            }
        };
        timer.schedule(task, 5000);
    }

    public void startRotateAnimation(ImageView imageView) {
        Animation an = new RotateAnimation(ROTATE_FROM, ROTATE_TO,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        an.setDuration(2000);
        an.setInterpolator(new LinearInterpolator());
        an.setRepeatMode(Animation.INFINITE);
        an.setRepeatCount(200);
        imageView.startAnimation(an);
    }

    private void startFlipping() {
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
                this, R.anim.flip_text_out));
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                R.anim.flip_text_in));
        mViewFlipper.setFlipInterval(1000);
        mViewFlipper.startFlipping();

        mViewFlipper.getInAnimation().setAnimationListener(
                new Animation.AnimationListener() {

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {

                        int displayedChild = mViewFlipper.getDisplayedChild();
                        int childCount = mViewFlipper.getChildCount();

                        if (displayedChild == childCount - 1) {
                            mViewFlipper.stopFlipping();
                        }
                    }
                });
    }
}
