package com.example.lxx4.Frag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        sql sql1 = new sql(context);
        db = sql1.getWritableDatabase();
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
    public  String queryData(String url){
        String json ="";
        Cursor cursor = db.query("student", null, "url=?", new String[]{url}, null, null, null, null);
        while (cursor.moveToNext()){
            json = cursor.getString(cursor.getColumnIndex("json"));
        }
        return json;
    }

   /* // 查询
    public Cursor queryWithFactory(String table, String[] columns,
                                   String selection, String[] selectionArgs, String groupBy,
                                   String having, String orderBy) {
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }*/

   /* // 添加
    public long insert(String table, String nullColumnHack, ContentValues values) {
        return db.insert(table, null, values);
    }


    // 删除

    public int delete(String table, String whereClause, String[] whereArgs) {
        return db.delete(table, whereClause, whereArgs);
    }*/
}
