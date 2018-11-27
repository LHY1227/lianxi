package com.example.liuhongyin20181119.Frag;

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
import android.widget.TableLayout;

import com.example.liuhongyin20181119.R;

import java.util.ArrayList;

public class Frag_01 extends Fragment {

    private TabLayout tl;
    private ViewPager pager;
    ArrayList<Fragment> flist = new ArrayList<Fragment>();
    ArrayList<String> slist = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01,container,false);

        tl = view.findViewById(R.id.tl);
        pager = view.findViewById(R.id.pager);


        //tl.setTabMode(TabLayout.MODE_FIXED);








        Myadapter myadapter = new Myadapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(myadapter);




        initData();
        return view;
    }

    private void initData() {
        for(int i=0;i<6;i++){
            slist.add("热门"+i);
            flist.add(new BlankFragment());
        }
    }

    class Myadapter extends FragmentPagerAdapter{

        public Myadapter(FragmentManager fm) {
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
