package com.example.liuhongyin20181119;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.liuhongyin20181119.Frag.Frag_01;
import com.example.liuhongyin20181119.Frag.Frag_02;
import com.example.liuhongyin20181119.Frag.Frag_03;
import com.example.liuhongyin20181119.Frag.Frag_04;
import com.example.liuhongyin20181119.Frag.Frag_05;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;
    private Frag_04 frag_04;
    private Frag_05 frag_05;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);

        ArrayList<Fragment> list = new ArrayList<Fragment>();
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();
        frag_04 = new Frag_04();
        frag_05 = new Frag_05();



        manager = getSupportFragmentManager();
        FragmentTransaction f1 = manager.beginTransaction();
        f1.add(R.id.fl,frag_01).show(frag_01);
        f1.add(R.id.fl,frag_02).hide(frag_02);
        f1.add(R.id.fl,frag_03).hide(frag_03);
        f1.add(R.id.fl,frag_04).hide(frag_04);
        f1.add(R.id.fl,frag_05).hide(frag_05);
        f1.commit();





        rg.check(rg.getChildAt(0).getId());
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction f3 = manager.beginTransaction();
                switch (checkedId){
                    case 1:
                        f3.show(frag_01).hide(frag_02).hide(frag_03).hide(frag_04).hide(frag_05);
                        break;
                    case 2:
                        f3.show(frag_02).hide(frag_01).hide(frag_03).hide(frag_04).hide(frag_05);
                        break;
                    case 3:
                        f3.show(frag_03).hide(frag_02).hide(frag_01).hide(frag_04).hide(frag_05);
                        break;
                    case 4:
                        f3.show(frag_04).hide(frag_02).hide(frag_03).hide(frag_01).hide(frag_05);
                        break;
                    case 5:
                        f3.show(frag_05).hide(frag_02).hide(frag_03).hide(frag_04).hide(frag_01);
                        break;
                }
                f3.commit();
            }
        });


    }
}
