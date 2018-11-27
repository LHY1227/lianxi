package com.example.lxx5;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.lxx5.Frag.Frag_01;
import com.example.lxx5.Frag.Frag_02;
import com.example.lxx5.Frag.Frag_03;
import com.example.lxx5.cela.CMyAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class TwoActivity3 extends AppCompatActivity {

    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private FrameLayout fl;
    private RadioGroup rg;
    private LinearLayout ll;
    private ImageView iv;
    private ListView lv;
    private DrawerLayout dl;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;
    private FragmentManager manager;
    private FragmentTransaction f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two3);

        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);
        ll = findViewById(R.id.ll);
        iv = findViewById(R.id.iv);
        lv = findViewById(R.id.lv);
        dl = findViewById(R.id.dl);

        ArrayList<Fragment> list = new ArrayList<Fragment>();
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();

        manager = getSupportFragmentManager();
        FragmentTransaction f1 = manager.beginTransaction();
        f1.add(R.id.fl,frag_01,"frag_01").show(frag_01);
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




    //侧拉
        ArrayList<String> clist = new ArrayList<String>();
        clist.add("第一个页面");
        clist.add("第二个页面");
        clist.add("第三个页面");
        CMyAdapter cMyAdapter = new CMyAdapter(this,clist);
        lv.setAdapter(cMyAdapter);
    //圆角图片
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(urlBitmap,iv,Images.getImg());

    //点击跳转
        f3 = manager.beginTransaction();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        f3.show(frag_01).hide(frag_03).hide(frag_02);
                        rg.check(rg.getChildAt(0).getId());
                        break;
                    case 1:
                        f3.show(frag_02).hide(frag_03).hide(frag_01);
                        rg.check(rg.getChildAt(1).getId());
                        break;
                    case 2:
                        f3.show(frag_03).hide(frag_01).hide(frag_02);
                        rg.check(rg.getChildAt(2).getId());
                        break;
                }
                dl.closeDrawers();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment frag_01a = manager.findFragmentByTag("frag_01");
        frag_01a.onActivityResult(requestCode,resultCode,data);
    }
}
