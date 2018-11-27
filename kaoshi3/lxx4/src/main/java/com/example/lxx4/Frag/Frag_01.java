package com.example.lxx4.Frag;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.xlistviewlibrary.utils.NetWordUtils;
import com.example.lxx4.MainActivity;
import com.example.lxx4.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class Frag_01 extends Fragment {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";  //（备用接口）
    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private Banner banner;
    private PullToRefreshListView pull;
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    ArrayList<String> blist = new ArrayList<String>();
    private MyAdapter myAdapter;
    int page;
    private Dao dao;
    private String st;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01,container,false);

        dao = new Dao(getActivity());
        banner = view.findViewById(R.id.banner);
        pull = view.findViewById(R.id.pull);

        blist.add(urlBitmap);
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        banner.setImageLoader(new ImageLoder1());
        banner.setImages(blist);
        banner.start();


        myAdapter = new MyAdapter(getActivity(),list);
        pull.setAdapter(myAdapter);
        initData(1);

        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                page=1;
                initData(page);
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
        if(Internet.getNet(getActivity())){
            new MAsTask().execute(st);
        }
        else {
            Toast.makeText(getActivity(), "请联网，谢谢！", Toast.LENGTH_SHORT).show();
            String s = dao.queryData(st);
            if (!s.isEmpty()){
                Gson gson=new Gson();
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

            Gson gson=new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();
        }
    }

    class ImageLoder1 extends com.youth.banner.loader.ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageLoader instance = ImageLoader.getInstance();
            instance.displayImage((String) path,imageView);
        }
    }
}
