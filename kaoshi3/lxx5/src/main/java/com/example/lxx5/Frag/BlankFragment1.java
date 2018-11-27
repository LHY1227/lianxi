package com.example.lxx5.Frag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.xlistviewlibrary.utils.NetWordUtils;
import com.example.lxx5.Frag.sql.Dao;
import com.example.lxx5.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
class BlankFragment1 extends android.support.v4.app.Fragment {

    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    private Banner banner;
    private PullToRefreshListView pull;
    ArrayList<String> blist =  new  ArrayList<String>();
    private MyAdapter myAdapter;
    int page;
    private Dao dao;
    private String st;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1,container,false);

        banner = view.findViewById(R.id.banner);
        pull = view.findViewById(R.id.pull);
        dao = new Dao(getActivity());


        //轮播图
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        banner.setImageLoader(new ImageLoders());
        banner.setImages(blist);
        banner.start();

        //上下拉刷新
        myAdapter = new MyAdapter(getActivity(),list);
        pull.setAdapter(myAdapter);
        initData(0);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                page=0;
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

    private void initData(int page) {
        st = urlString+page;
        if (Internet.getNet(getContext())){
            new MAsTask().execute(st);
        }
        else {
            Toast.makeText(getActivity(), "请联网，谢谢！", Toast.LENGTH_SHORT).show();
            String s = dao.queryData(st);

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

    //异步请求
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


    //轮播图
    class ImageLoders extends com.youth.banner.loader.ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageLoader instance = ImageLoader.getInstance();
            instance.displayImage((String) path,imageView);
        }
    }
}
