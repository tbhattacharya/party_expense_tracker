package com.expense.tamal.expenseshare.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.expense.tamal.expenseshare.ExpenseShareApplication;

/**
 * Created by tamal on 20/09/2016.
 */
public class ExpenseDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    private final static String TAG = "ExpenseDbHelper";

    public static String DB_NAME = "expense_data";

    private static ExpenseDbHelper expenseDbHelper;


    private ExpenseDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static void init(Context context) {
        if (expenseDbHelper == null) {
            expenseDbHelper = new ExpenseDbHelper(context);
        }

    }

    public static ExpenseDbHelper getSingleton() {
        try {
            if (expenseDbHelper == null) {
                if (ExpenseShareApplication.getInstance() != null) {
                    init(ExpenseShareApplication.getInstance());
                } else {
                    throw new Exception(
                            "Application is null in ExpenseDbHelper");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating tables");
        db.execSQL(EventsDBHelper.getCreateTableQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
