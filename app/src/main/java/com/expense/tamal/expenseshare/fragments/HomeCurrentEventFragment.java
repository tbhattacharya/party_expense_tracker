package com.expense.tamal.expenseshare.fragments;


import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.views.BudgetModule;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCurrentEventFragment extends Fragment {

    @BindView(R.id.budget_overview)
    protected BudgetModule mBudgetModule;
    @BindView(R.id.scrollView)
    protected ScrollView scrollView;

    @BindView(R.id.testButton)
    protected Button testButton;
    private Handler mUpHandler = new Handler();

    private Runnable animateUpImage = new Runnable() {

        @Override
        public void run() {
            checkAndAnimatePOD();
        }
    };

    public HomeCurrentEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_current_event, container, false);
        ButterKnife.bind(this, view);
        mBudgetModule.setupBudgetView(2000, 1000);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndAnimatePOD();
            }
        });
        return view;
    }


    private void checkAndAnimatePOD() {
        try {
            if (mBudgetModule != null
                    && mBudgetModule.getVisibility() == View.VISIBLE
                    && !mBudgetModule.isAnimationShown()) {
                Rect scrollBounds = new Rect(scrollView.getScrollX(),
                        scrollView.getScrollY(), scrollView.getScrollX()
                        + scrollView.getWidth(),
                        scrollView.getScrollY() + scrollView.getHeight());
                Rect tierPointModuleBounds = new Rect(
                        mBudgetModule.getLeft(), mBudgetModule.getTop(),
                        mBudgetModule.getLeft() + mBudgetModule.getWidth(),
                        mBudgetModule.getTop() + mBudgetModule.getHeight());

                mBudgetModule.startAnimationIfOnScreenIfRequired(
                        scrollBounds, tierPointModuleBounds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
