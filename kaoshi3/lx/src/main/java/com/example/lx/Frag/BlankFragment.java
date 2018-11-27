package com.example.lx.Frag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.gsm.GsmCellLocation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lx.MainActivity;
import com.example.lx.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
class BlankFragment extends android.support.v4.app.Fragment {

    String url = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private PullToRefreshListView pull;
    private Banner banner;
    ArrayList<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    private MyAdapter myAdapter;
    int page=0;
    private Dao dao;
    private String st;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1,container,false);

        pull = view.findViewById(R.id.pull);
        banner = view.findViewById(R.id.banner);

        dao = new Dao(getActivity());
        ArrayList<String> blist = new ArrayList<String>();
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        blist.add(urlBitmap);
        banner.setImageLoader(new Images());
        banner.setImages(blist);
        banner.start();


        myAdapter = new MyAdapter(getActivity(),list);
        pull.setAdapter(myAdapter);

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



        initData(0);
        return view;
    }

    private void initData(int page) {
        st = url+page;
        if (Internet.getNet(getActivity())){
            new MAsTask().execute(st);
        }
        else {
            Toast.makeText(getActivity(), "请联网，谢谢！", Toast.LENGTH_SHORT).show();

            String s = dao.queryData(st);
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();
        }

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


            dao.insertData(st,s);

            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
            pull.onRefreshComplete();



        }
    }

    class Images extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader instance = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            instance.displayImage((String) path,imageView);
        }
    }
}
