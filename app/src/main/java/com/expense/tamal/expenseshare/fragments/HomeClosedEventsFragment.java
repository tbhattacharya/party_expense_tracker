package com.expense.tamal.expenseshare.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.activity.HomeActivity;
import com.expense.tamal.expenseshare.adapters.HomeEventListAdapter;
import com.expense.tamal.expenseshare.managers.EventManager;
import com.expense.tamal.expenseshare.views.SlideShowView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tamal on 29/09/2016.
 */
public class HomeClosedEventsFragment extends Fragment {

    @BindView(R.id.previous_events)
    protected ListView eventsList;
    @BindView(R.id.slide_show)
    protected SlideShowView mSlideShowView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_home_closed_events, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI(){
        mSlideShowView.setmStrings(EventManager.getSingleton().getAllEventNames());
        mSlideShowView.setMyImageListFromFile(EventManager.getSingleton().getAllEventsImages());
        eventsList.setAdapter(new HomeEventListAdapter((HomeActivity)getActivity(), EventManager.getSingleton().getAllEvents()));
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
