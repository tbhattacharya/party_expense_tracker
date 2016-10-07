package com.expense.tamal.expenseshare.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.expense.tamal.expenseshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tamal on 07/10/2016.
 */
public class HomeMembersModule extends LinearLayout {

    @BindView(R.id.members_list)
    protected ListView mMembersList;

    public HomeMembersModule(Context context) {
        super(context);
        init();
    }

    public HomeMembersModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeMembersModule(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            View view = inflater.inflate(R.layout.members_layout_home_fragment, this);
            ButterKnife.bind(this, view);
        }
    }
}
