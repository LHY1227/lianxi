package com.example.lxx2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }


    public void insertData(String url, String json) {
        db.delete("student","url=?",new String[]{url});

        ContentValues values = new ContentValues();
        values.put("url",url);
        values.put("json",json);
        db.insert("student","null",values);
    }

    public String queryData(String url){
        String json ="";

        Cursor cursor = db.query("student", null, "url=?", new String[]{url}, null, null, null, null);
        while (cursor.moveToNext()){
            json = cursor.getString(cursor.getColumnIndex("json"));
        }

        return json;
    }


}
