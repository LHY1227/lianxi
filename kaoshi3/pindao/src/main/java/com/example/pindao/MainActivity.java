package com.example.pindao;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tl;
    private Button button;
    private ViewPager pager;
    List<ChannelBean> channelBeanList = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initView() {
        tl = findViewById(R.id.tl);
        button = findViewById(R.id.button);
        pager = findViewById(R.id.pager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelActivity.startChannelActivity(MainActivity.this,channelBeanList);
            }
        });
    }

    private void initData() {
        channelBeanList.add(new ChannelBean("推荐",true));
        channelBeanList.add(new ChannelBean("推荐",true));
        channelBeanList.add(new ChannelBean("推荐",true));
        channelBeanList.add(new ChannelBean("条目一",false));
        channelBeanList.add(new ChannelBean("条目二",false));
        channelBeanList.add(new ChannelBean("条目三",false));

        for (int i=0;i<channelBeanList.size();i++){
            if (channelBeanList.get(i).isSelect()){
                String name = channelBeanList.get(i).getName();
                tl.addTab(tl.newTab().setText(name));
                fragments.add(new MFragment());
            }
        }

        //设置适配器
        myAdapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(myAdapter);

        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setupWithViewPager(pager);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String json = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
        Type type = new TypeToken<List<ChannelBean>>() {}.getType();
        Gson gson = new Gson();
        // 将集合的数量进行更新
        channelBeanList = gson.fromJson(json, type);
        // 将TabLayout的头部文字清空
        tl.removeAllTabs();
        // 将页面集合清空
        fragments.clear();

        // 重新向集合中添加数据
        for (int i = 0; i < channelBeanList.size(); i++) {
            if (channelBeanList.get(i).isSelect()) {
                String name = channelBeanList.get(i).getName();
                tl.addTab(tl.newTab().setText(name));
                fragments.add(new MFragment());
            }
        }
        // 更新适配器
        myAdapter.notifyDataSetChanged();
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return channelBeanList.get(position).getName();
        }
    }
}
