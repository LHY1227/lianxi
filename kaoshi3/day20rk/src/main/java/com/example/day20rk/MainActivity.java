package com.example.day20rk;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tl;
    ArrayList<String> slist = new ArrayList<String>();
    ArrayList<Fragment> flist = new ArrayList<Fragment>();
    private TMyAdapter tMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        tl = findViewById(R.id.tl);
        initData();
        tMyAdapter = new TMyAdapter(getSupportFragmentManager());
        pager.setAdapter(tMyAdapter);


        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setupWithViewPager(pager);



    }

    private void initData() {
        for (int i=0;i<2;i++){
            slist.add("热门"+i);
            flist.add(new BlankFragment());
        }
    }

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
            return slist.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return slist.get(position);
        }
    }
}
