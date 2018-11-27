package com.example.day20rk;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwie.xlistviewlibrary.utils.NetWordUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
class BlankFragment extends android.support.v4.app.Fragment {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";  //（备用接口）
    private PullToRefreshListView pull;
    private MyAdapter myAdapter;
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    int page;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1,container,false);

        pull = view.findViewById(R.id.pull);
        myAdapter = new MyAdapter(getContext(),list);
        pull.setAdapter(myAdapter);
        initData(0);

        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                initData(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                initData(page);
            }
        });


        return view;
    }

    private void initData(int pa) {
        new MAsTask().execute(urlString+page);
    }

    class MAsTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String p = NetWordUtils.getNetjson(strings[0]);
            return p;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();
        }
    }
}
