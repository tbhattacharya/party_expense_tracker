package com.expense.tamal.expenseshare;

import android.app.Application;
import android.os.AsyncTask;

import com.expense.tamal.expenseshare.activity.SplashActivity;
import com.expense.tamal.expenseshare.databases.ExpenseDbHelper;

/**
 * Created by tamal on 20/09/2016.
 */
public class ExpenseShareApplication extends Application{

    public static ExpenseShareApplication mInstance;

    public static synchronized ExpenseShareApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        new InitTask().execute();
    }

    public void init(){
        ExpenseDbHelper.getSingleton().getWritableDatabase();
    }

    private class InitTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            init();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SplashActivity.launchHomeScreen();
        }
    }
}
