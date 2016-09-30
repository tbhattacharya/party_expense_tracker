package com.expense.tamal.expenseshare.databases;

/**
 * Created by tamal on 20/09/2016.
 */
public class EventsDBHelper {



    public static String getCreateTableQuery(){
        return "CREATE TABLE " + EventsTable.TABLE + "("
                + EventsTable.EVENT_ID + "varchar2, "
                + EventsTable.EVENT_MEMBERS + "varchar2, "
                + EventsTable.EVENT_DATE + "varchar2, "
                + EventsTable.EVENT_TYPE + "varchar2, "
                + EventsTable.EVENT_COST + "varchar2, "
                + EventsTable.EVENT_CURRENCY + "varchar2, "
                + EventsTable.EVENT_BUDGET + "varchar2, "
                + EventsTable.EVENT_PHOTO + "varchar2);";
    }


    private final class EventsTable{
        public static final String TABLE = "EventsTable";
        public static final String EVENT_ID = "EVENT_ID";
        public static final String EVENT_MEMBERS = "EVENT_MEMBERS";
        public static final String EVENT_DATE = "EVENT_DATE";
        public static final String EVENT_TYPE = "EVENT_TYPE";
        public static final String EVENT_COST = "EVENT_COST";
        public static final String EVENT_CURRENCY = "EVENT_CURRENCY";
        public static final String EVENT_PHOTO = "EVENT_PHOTO";
        public static final String EVENT_BUDGET = "EVENT_BUDGET";
    }
}
