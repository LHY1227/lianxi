package com.example.lxx4.Frag;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lxx4.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Frag_02 extends Fragment {


    private Dao dao;
    private TextView tv;
    ArrayList<String> list = new ArrayList<String>();

    public String urlString00 = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";  //（备用接口）
    private String json;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02,container,false);

        tv = view.findViewById(R.id.tv);

        dao = new Dao(getContext());
        String urlString = dao.queryData(urlString00);
        Log.e("ljt","=======urlstring======="+urlString);

        Gson gson=new Gson();
        Bean bean = gson.fromJson(urlString, Bean.class);
        List<Bean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            String news_title = data.get(i).getNews_title();
            list.add(news_title);
        }

        String s = list.toString();
        Log.e("www","============s========="+s);
        tv.setText(list.toString());

        return view;
    }
}
