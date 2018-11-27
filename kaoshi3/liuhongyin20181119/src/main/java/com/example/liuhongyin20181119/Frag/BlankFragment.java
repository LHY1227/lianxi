package com.example.liuhongyin20181119.Frag;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.liuhongyin20181119.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
class BlankFragment extends android.support.v4.app.Fragment {

    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    String url ="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private Banner banner;
    private PullToRefreshListView pull;
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    private MyAdapter myAdapter;
    int page;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item,container,false);

        banner = view.findViewById(R.id.banner);
        pull = view.findViewById(R.id.pull);
        initData(0);
        ArrayList<String> blist = new ArrayList<String>();
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        blist.add(urlBitmap);


        //banner.setImageLoader(new ImageLoader());
        banner.setImages(blist);
        banner.start();

        myAdapter = new MyAdapter(getActivity(),list);
        pull.setAdapter(myAdapter);






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



    private void initData(int i) {
        new MAsTask().execute(url+page);
    }

    class MAsTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            String p = NetUtil.getJson(strings[0]);
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
