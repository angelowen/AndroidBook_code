package com.example.app.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    final static String DB_Name = "ContactsDB";
    final static int DB_Version = 1;
    final static String CONTACT_TABLE = "contacts";
    final static String CONTACT_ID = "id";
    final static String CONTACT_NAME = "name";
    final static String CONTACT_Phone = "phone";

    public MySQLiteOpenHelper(Context context){
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table " + CONTACT_TABLE + "("
                + CONTACT_ID + " integer primary key autoincrement, "
                + CONTACT_NAME + " text, "
                + CONTACT_Phone + " text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + CONTACT_TABLE);
        onCreate(db);
    }

}
