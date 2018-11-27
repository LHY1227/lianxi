package com.example.lxx5.Frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.lxx5.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Frag_01 extends Fragment {

    private TabLayout tl;
    private ViewPager pager;
    ArrayList<Fragment> flist = new ArrayList<Fragment>();
    ArrayList<ChannelBean> slist = new ArrayList<>();
    private TMyAdapter tMyAdapter;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01 ,container,false);

        tl = view.findViewById(R.id.tl);
        pager = view.findViewById(R.id.pager);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(),slist);
            }
        });


        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setupWithViewPager(pager);
        initData();
        tMyAdapter = new TMyAdapter(getChildFragmentManager());
        pager.setAdapter(tMyAdapter);

        return view;
    }

    private void initData() {
        slist.add(new ChannelBean("推荐",true));
        slist.add(new ChannelBean("热门",true));
        slist.add(new ChannelBean("我的",true));

        slist.add(new ChannelBean("1",false));
        slist.add(new ChannelBean("2",false));
        slist.add(new ChannelBean("3",false));

        for (int i= 0;i<slist.size();i++){
            if (slist.get(i).isSelect()){
                String name = slist.get(i).getName();
                tl.addTab(tl.newTab().setText(name));
                if (i==0){
                    flist.add(new BlankFragment1());
                }
                else if(i==1){
                    flist.add(new BlankFragment2());
                }
            }
        }

    }




    //flist.add(new BlankFragment1());
    //flist.add(new BlankFragment2());
    class TMyAdapter extends FragmentPagerAdapter{

        public TMyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return flist.get(i);
        }

        @Override
        public int getCount() {
            return flist.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return slist.get(position).getName();
        }
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String json = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
        Type type = new TypeToken<List<ChannelBean>>() {
        }.getType();

        slist = new Gson().fromJson(json, type);
        tl.removeAllTabs();
        flist.clear();
        for (int i= 0;i<slist.size();i++){
            if (slist.get(i).isSelect()){
                String name = slist.get(i).getName();
                tl.addTab(tl.newTab().setText(name));
                if (i==0){
                    flist.add(new BlankFragment1());
                }
                else if(i==1){
                    flist.add(new BlankFragment2());
                }
                else{
                    flist.add(new BlankFragment2());
                }
            }
        }

        tMyAdapter.notifyDataSetChanged();
//        flist.clear();
    }*/
}
