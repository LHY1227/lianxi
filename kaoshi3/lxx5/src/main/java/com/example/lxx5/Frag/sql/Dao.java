package com.example.lxx5.Frag.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Dao {

    private final SQLiteDatabase db;
    private ContentValues values;

    public Dao(Context context) {
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    //先删除在添加
    public void insertData(String url, String json) {
        db.delete("student", "url=?", new String[]{url});

        values = new ContentValues();
        values.put("url", url);
        values.put("json", json);
Log.e("jj","+++++++++++++++++"+url);
        long student = db.insert("student", null, values);
        Log.e("lhy", "===================values=================" + student+"");
    }

    //查询的方法
    public String queryData(String url) {
        String json = "";
        Cursor cursor = db.query("student", null, "url=?", new String[]{url}, null, null, null);
        if (cursor.moveToFirst()) {
            do{
                json = cursor.getString(cursor.getColumnIndex("json"));
            }while (cursor.moveToNext());
        }
Log.e("lhy","==========================*****"+json);
        return json;
    }
}
