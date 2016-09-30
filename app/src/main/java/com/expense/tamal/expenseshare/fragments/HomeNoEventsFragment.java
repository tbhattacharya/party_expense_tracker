package com.expense.tamal.expenseshare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.views.SlideShowView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeNoEventsFragment extends Fragment {


    @BindView(R.id.slide_show)
    protected SlideShowView mSlideShowView;
    @BindArray(R.array.steps_array)
    protected String[] mStrings;

    private int[] myImageList = new int[]{R.drawable.step_1, R.drawable.step_2, R.drawable.step_3, R.drawable.step_4};


    public HomeNoEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_no_events, container, false);
        ButterKnife.bind(this, view);
        mSlideShowView.setmStrings(new ArrayList<>(Arrays.asList(mStrings)));
        mSlideShowView.setMyImageListFromDrawable(myImageList);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSlideShowView.startSlideShow();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSlideShowView.stopSlideShow();
    }
}
