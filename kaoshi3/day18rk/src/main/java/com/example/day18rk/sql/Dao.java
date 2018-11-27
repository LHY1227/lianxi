package com.example.day18rk.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.day18rk.MainActivity;

public class Dao {

    private final SQLiteDatabase db;


    public Dao(MainActivity mainActivity){
        SQLite sqLite = new SQLite(mainActivity);
        db = sqLite.getWritableDatabase();
    }

    //先删除在加载
    public void insertData(String url,String json){
        db.delete("student","url=?",new String[]{url});

        ContentValues values = new ContentValues();
        values.put("url",url);
        values.put("json",json);
        long insert = db.insert("student", null, values);

    }



    public String queryData(String st) {

        String json = "";

        Cursor cursor = db.query("student", null, "url=?", new String[]{st}, null, null, null, null);
        while (cursor.moveToNext()){
            json = cursor.getString(cursor.getColumnIndex("json"));
        }
        return json;
    }
}
