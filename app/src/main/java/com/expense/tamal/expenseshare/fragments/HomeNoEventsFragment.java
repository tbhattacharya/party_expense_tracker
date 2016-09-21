package com.expense.tamal.expenseshare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expense.tamal.expenseshare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeNoEventsFragment extends Fragment {


    public HomeNoEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_no_events, container, false);
    }

}
