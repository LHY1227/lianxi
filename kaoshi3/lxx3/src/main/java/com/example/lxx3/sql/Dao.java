package com.example.lxx3.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lxx3.MainActivity;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(MainActivity mainActivity){
        SQLite sqLite = new SQLite(mainActivity);
        db = sqLite.getWritableDatabase();
    }

    //先删除在添加
    public void insertData(String url,String json){
        db.delete("student","url=?",new String[]{url});

        ContentValues values = new ContentValues();
        values.put("url",url);
        values.put("json",json);
        long insert = db.insert("student", null, values);
    }

    //查询的方法
    public String queryData(String url){
        String json="";
        Cursor cursor = db.query("student", null, "url=?", new String[]{url}, null, null, null, null);
        while (cursor.moveToNext()){
            json = cursor.getString(cursor.getColumnIndex("json"));
        }

        return json;
    }
}
