package com.example.lxx5.Frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lxx5.Frag.sql.Dao;
import com.example.lxx5.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Frag_02 extends Fragment {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private Dao dao;
    private TextView tv4;
    ArrayList<String> list = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02 ,container,false);

        dao = new Dao(getContext());
        tv4 = view.findViewById(R.id.tv4);


        String s = dao.queryData(urlString);
        Gson gson = new Gson();
        Log.e("lhy","========================"+s);
        Bean bean = gson.fromJson(s, Bean.class);
        List<Bean.DataBean> data = bean.getData();

        for (int i=0;i<data.size();i++){
            String news_title = data.get(i).getNews_title();
            String news_summary = data.get(i).getNews_summary();
            list.add(news_title);
            list.add(news_summary);
        }

        String s1 = list.toString();
        Log.e("www","============s========="+s1);
        tv4.setText(s1);
        return view;
    }
}
