package com.example.lx.Frag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lx.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")


class BlankFragment2 extends android.support.v4.app.Fragment {

    String url = "http://api.expoon.com/AppNews/getNewsList/type/1/p/0";
    private TextView tv;
    ArrayList<String> strings = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f2,container,false);

        tv = view.findViewById(R.id.tv22);
        Dao dao = new Dao(getContext());
        ArrayList<Bean.DataBean> list = new ArrayList<>();
        String s = dao.queryData(url);

        //tv.setText(s);
        Gson gson = new Gson();
        Bean bean = gson.fromJson(s, Bean.class);
        List<Bean.DataBean> data = bean.getData();
        list.addAll(data);
        for (int i = 0;i<list.size(); i++){
            String news_title = list.get(i).getNews_title();
            String news_id = list.get(i).getNews_id();
            String news_summary = list.get(i).getNews_summary();
            strings.add(news_id);
            strings.add(news_title);
            strings.add(news_summary);
        }

        String s1 = strings.toString();
        tv.setText(s1);

        return view;
    }
}
