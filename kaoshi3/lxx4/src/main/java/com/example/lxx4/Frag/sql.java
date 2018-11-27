package com.example.lxx4.Frag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sql extends SQLiteOpenHelper {
    public sql(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table zk3a(table_id integer primary key autoincrement,url text,id text,title text,content text)");
        db.execSQL("create table student (id Integer primary key autoincrement ,url text,json text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
