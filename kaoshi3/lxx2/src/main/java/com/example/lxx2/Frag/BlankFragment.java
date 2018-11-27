package com.example.lxx2.Frag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.xlistviewlibrary.utils.NetWordUtils;
import com.example.lxx2.R;
import com.example.lxx2.sql.Dao;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
class BlankFragment extends android.support.v4.app.Fragment {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private Banner banner;
    private PullToRefreshListView pull;
    private MyAdapter myAdapter;
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    int page;
    private String st;
    private Dao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1,container,false);

        dao = new Dao(getContext());
        banner = view.findViewById(R.id.banner);
        pull = view.findViewById(R.id.pull);

        pull.setMode(PullToRefreshBase.Mode.BOTH);


        myAdapter = new MyAdapter(getActivity(),list);
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





        ArrayList<String> blist = new ArrayList<String>();
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        blist.add(urlBitmap);

        banner.setImages(blist);
        banner.setImageLoader(new ImageLoder());
        banner.start();

        return view;
    }

    private void initData(int page) {
        st = urlString+page;
        if (InterNet.getNet(getContext())){
            new MAsTask().execute(st);
        }
        else{
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

    class ImageLoder extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            instance.displayImage((String) path,imageView);
        }
    }
}
