package com.example.lx.Frag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lx.MainActivity;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        SQLite sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    //先删除再添加
    public void insertData(String url,String json){
        db.delete("student","url=?",new String[]{url});

        ContentValues values = new ContentValues();
        values.put("url",url);
        values.put("json",json);
        long insert = db.insert("student", null, values);
        Log.e("sss","======================insert==========================="+insert);

    }

    //查询
    public String queryData(String url){
        String json="";
        Cursor cursor = db.query("student", null, "url=?", new String[]{url}, null, null, null, null);
        while (cursor.moveToNext()){
            json = cursor.getString(cursor.getColumnIndex("json"));
        }
        Log.e("sss","======================json==========================="+json);
        return json;

    }
}
