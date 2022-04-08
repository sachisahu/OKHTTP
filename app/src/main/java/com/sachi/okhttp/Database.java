package com.sachi.okhttp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonUiContext;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    static String DATABASE_NAME = "bookingTicket.db";
    String TABLE_NAME = "bookedTicket";
    String COLUMN_TICKET_NO = "tktNo";
    String COLUMN_RACE_NAME = "race";
    String COLUMN_RACE_DATE = "date";
    String COLUMN_RACE_TIME = "time";
    String COLUMN_TICKET_UNITS = "units";
    String COLUMN_TICKET_AMOUNT = "totalAmt";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE bookedTicket(id integer primary key ,tktNo text,race text,date text,time text,units text,totalAmt text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insetTicket(String txtno,String racename,String date,String time,String units,String totalamt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TICKET_NO,txtno);
        values.put(COLUMN_RACE_NAME,racename);
        values.put(COLUMN_RACE_DATE,date);
        values.put(COLUMN_RACE_TIME,time);
        values.put(COLUMN_TICKET_UNITS,units);
        values.put(COLUMN_TICKET_AMOUNT,totalamt);
        db.insert(TABLE_NAME,null,values);
        return true;
    }
}
