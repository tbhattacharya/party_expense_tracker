package com.expense.tamal.expenseshare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.adapters.EventCategoryAdapters;
import com.expense.tamal.expenseshare.enums.EventCategoryEnum;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventsFragment extends Fragment {


    @BindView(R.id.event_category)
    protected EditText eventCategory;
    @BindView(R.id.event_image)
    protected ImageButton takeImage;

    private ListPopupWindow lpw;
    private List<EventCategoryEnum> eventCategoryEnumList;

    public AddEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_events, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initUI() {
        lpw = new ListPopupWindow(getActivity());
        eventCategoryEnumList = new ArrayList<>(EnumSet.allOf(EventCategoryEnum.class));
        final EventCategoryAdapters adapters = new EventCategoryAdapters(getActivity(), eventCategoryEnumList);
        lpw.setAdapter(adapters);
        lpw.setAnchorView(eventCategory);
        lpw.setModal(true);

        eventCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    lpw.show();
                    return true;
                }
                return false;
            }
        });

        lpw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eventCategory.setText(eventCategoryEnumList.get(position).categoryName);
                lpw.dismiss();
            }
        });
    }
}
