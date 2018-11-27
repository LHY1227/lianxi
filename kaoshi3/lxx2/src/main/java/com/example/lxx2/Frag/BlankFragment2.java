/*
package com.example.lxx2.Frag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxx2.R;
import com.example.lxx2.sql.Dao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
class BlankFragment2 extends android.support.v4.app.Fragment {

    private TextView tv22;
    String url = "http://api.expoon.com/AppNews/getNewsList/type/1/p/0";
    ArrayList<String> list2 = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item2,container,false);


        tv22 = view.findViewById(R.id.tv22);
        Dao dao = new Dao(getContext());

        ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();

        String s = dao.queryData(url);

        //tv22.setText(s);
        //Toast.makeText(getActivity(), "哈哈"+s, Toast.LENGTH_SHORT).show();
       //Log.e("jjj","哈哈哈"+s);
        Gson gson = new Gson();
        Bean bean = gson.fromJson(s, Bean.class);
        List<Bean.DataBean> data = bean.getData();
        list.addAll(data);

        for (int i=0;i<list.size();i++){
            String news_id = list.get(i).getNews_id();
            String news_title = list.get(i).getNews_title();
            String news_summary = list.get(i).getNews_summary();
            list2.add(news_id);
            list2.add(news_title);
            list2.add(news_summary);
        }

        String s1 = list2.toString();
        tv22.setText(s1);


        return view;
    }
}
*/
