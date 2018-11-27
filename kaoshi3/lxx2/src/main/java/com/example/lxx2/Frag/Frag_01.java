package com.example.lxx2.Frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lxx2.R;

import java.util.ArrayList;

public class Frag_01 extends Fragment {

    private ViewPager pager;
    private TabLayout tl;
    ArrayList<Fragment> flist = new ArrayList<Fragment>();
    ArrayList<String> slist = new ArrayList<String>();
    private Myadapter1 myadapter1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01,container,false);

        pager = view.findViewById(R.id.pager);
        tl = view.findViewById(R.id.tl);
        initData();

        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setupWithViewPager(pager);

        myadapter1 = new Myadapter1(getChildFragmentManager());
        pager.setAdapter(myadapter1);

        return view;
    }

    private void initData() {
        slist.add("热门");
        slist.add("推荐");
        flist.add(new BlankFragment());
        flist.add(new BlankFragment());
    }

    class Myadapter1 extends FragmentPagerAdapter{

        public Myadapter1(FragmentManager fm) {
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
