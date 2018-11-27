package com.example.day18rk;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.xlistviewlibrary.utils.NetWordUtils;
import com.example.day18rk.sql.Dao;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";  //（备用接口）
    private PullToRefreshListView pull;
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    private MyAdapter myAdapter;
    int page;
    private String st;
    private Dao dao;

    //权限
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new Dao(MainActivity.this);


        pull = findViewById(R.id.pull);

        myAdapter = new MyAdapter(MainActivity.this,list);
        pull.setAdapter(myAdapter);
        initData(0);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
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




    }

    private void initData(int page) {
        st = urlString+page;
        if(Internet.getNet(MainActivity.this)){
            new MAsTask().execute(st);
        }else{
            Toast.makeText(this, "请联网，谢谢！", Toast.LENGTH_SHORT).show();

            String s = dao.queryData(st);
            /*Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();*/
            if (!s.isEmpty()){
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                list.addAll(data);
                myAdapter.notifyDataSetChanged();
                pull.onRefreshComplete();

            }
        }

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

            dao.insertData(st,s);
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();

        }
    }
}
