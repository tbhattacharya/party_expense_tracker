package com.expense.tamal.expenseshare.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.activity.BaseActivity;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tamal on 29/09/2016.
 */
public class SlideShowView extends LinearLayout {

    @BindView(R.id.viewSwitcher)
    protected ViewSwitcher mViewSwitcher;
    protected TextView mProcessSteps;
    protected ImageView mStepsImage;
    private int step_count = 0;
    private int[] myImageListFromDrawable;
    private List<String> myImageListFromFile;
    protected List<String> mStrings;
    private Timer timer = new Timer();
    private final String TAG = "SlideShowView";

    private Context activity;

    public SlideShowView(Context context) {
        super(context);
        this.activity = context;
        initUI();
    }

    public SlideShowView(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.activity = context;
        initUI();
    }

    public SlideShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.activity = context;
        initUI();
    }

    private void initUI() {
        try {
            final LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                View view = inflater.inflate(R.layout.slide_show, this);
                ButterKnife.bind(this, view);
            }

            Animation in = AnimationUtils.loadAnimation(activity, R.anim.left_to_right_entry);
            Animation out = AnimationUtils.loadAnimation(activity, R.anim.left_to_right_exit);
            mViewSwitcher.setInAnimation(in);
            mViewSwitcher.setOutAnimation(out);
            mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    Log.d(TAG, "Inflate");
                    View mSwitchView = inflater.inflate(R.layout.image_steps, null);
                    mProcessSteps = (TextView) mSwitchView.findViewById(R.id.process_steps);
                    mStepsImage = (ImageView) mSwitchView.findViewById(R.id.steps_image);
                    return mSwitchView;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setMyImageListFromDrawable(int[] myImageListFromDrawable) {
        this.myImageListFromDrawable = myImageListFromDrawable;
    }

    public void setMyImageListFromFile(List<String> myImageListFromFile) {
        this.myImageListFromFile = myImageListFromFile;
    }

    public void setmStrings(List<String> mStrings) {
        this.mStrings = mStrings;
    }

    public void startSlideShow() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                changeSteps();
            }
        };
        timer.scheduleAtFixedRate(task, new Date(), 3000);
    }

    public void stopSlideShow() {
        timer.cancel();
    }

    private void changeSteps() {
        ((BaseActivity) activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myImageListFromDrawable != null) {
                    mStepsImage.setImageResource(myImageListFromDrawable[step_count]);
                } else if (myImageListFromFile != null) {
                    File imgFile = new File(myImageListFromFile.get(step_count));
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        mStepsImage.setImageBitmap(myBitmap);
                    }
                }
                mProcessSteps.setText(mStrings.get(step_count));
                if(mStrings.size() > 1) {
                    step_count = (step_count + 1) % mStrings.size();
                    mViewSwitcher.showNext();
                }
            }
        });

    }
}
