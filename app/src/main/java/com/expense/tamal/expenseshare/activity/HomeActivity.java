package com.expense.tamal.expenseshare.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.fragments.AddEventsFragment;
import com.expense.tamal.expenseshare.fragments.HomeClosedEventsFragment;
import com.expense.tamal.expenseshare.fragments.HomeCurrentEventFragment;
import com.expense.tamal.expenseshare.fragments.HomeNoEventsFragment;

public class HomeActivity extends BaseActivity {


    public static Intent getHomeActivityIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    @Override
    public void initUI() {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.fragment_container, new AddEventsFragment());
        transaction.commit();
    }

}
