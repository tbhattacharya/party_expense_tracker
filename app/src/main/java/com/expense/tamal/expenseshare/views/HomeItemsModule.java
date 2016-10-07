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
public class HomeItemsModule extends LinearLayout {

    @BindView(R.id.items_list)
    protected ListView mItemList;

    public HomeItemsModule(Context context) {
        super(context);
        init();
    }

    public HomeItemsModule(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeItemsModule(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            View view = inflater.inflate(R.layout.items_layout_home_fragment, this);
            ButterKnife.bind(this, view);
        }
    }
}
