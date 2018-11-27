package com.example.lx;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.lx.Frag.Frag_01;
import com.example.lx.Frag.Frag_02;
import com.example.lx.Frag.Frag_03;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private FrameLayout fl;
    private FragmentManager manager;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.rg);
        fl = findViewById(R.id.fl);

        manager = getSupportFragmentManager();
        FragmentTransaction f1 = manager.beginTransaction();

        ArrayList<Fragment> list = new ArrayList<Fragment>();
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();

        f1.add(R.id.fl,frag_01).show(frag_01);
        f1.add(R.id.fl,frag_02).hide(frag_02);
        f1.add(R.id.fl,frag_03).hide(frag_03);
        f1.commit();

        rg.check(rg.getChildAt(0).getId());
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction f2 = manager.beginTransaction();
                switch (checkedId){
                    case 1:
                        f2.show(frag_01).hide(frag_02).hide(frag_03);
                        break;
                    case 2:
                        f2.show(frag_02).hide(frag_01).hide(frag_03);
                        break;
                    case 3:
                        f2.show(frag_03).hide(frag_02).hide(frag_01);
                        break;
                }

                f2.commit();
            }
        });
    }
}
