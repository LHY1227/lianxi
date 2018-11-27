package com.example.lxx4;

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
import android.widget.Toast;

import com.example.lxx4.Frag.CMyAdapter;
import com.example.lxx4.Frag.Frag_01;
import com.example.lxx4.Frag.Frag_02;
import com.example.lxx4.Frag.Frag_03;
import com.example.lxx4.Frag.Image;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String urlBitmap = "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private LinearLayout ll;
    private FrameLayout fl;
    private RadioGroup rg;
    private ImageView iv1;
    private ListView lv1;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;
    private FragmentManager manager;
    ArrayList<String> clist = new ArrayList<String>();
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.ll);
        iv1 = findViewById(R.id.iv1);
        lv1 = findViewById(R.id.lv1);
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);
        dl = findViewById(R.id.dl);

        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(urlBitmap,iv1,Image.getImg());

        clist.add("第一个页面");
        clist.add("第二个页面");
        clist.add("第三个页面");
        clist.add("设置");
        CMyAdapter cMyAdapter = new CMyAdapter(MainActivity.this, clist);
        lv1.setAdapter(cMyAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction f3 = manager.beginTransaction();
                switch (position){
                    case 0:
                        f3.show(frag_01).hide(frag_02).hide(frag_03);
                        rg.check(rg.getChildAt(0).getId());
                        break;
                    case 1:
                        f3.show(frag_02).hide(frag_01).hide(frag_03);
                        rg.check(rg.getChildAt(1).getId());
                        break;
                    case 2:
                        f3.show(frag_03).hide(frag_02).hide(frag_01);
                        rg.check(rg.getChildAt(2).getId());
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "没有该页面！！！", Toast.LENGTH_SHORT).show();
                        break;
                }
                dl.closeDrawers();
            }
        });


        ArrayList<Fragment> list = new ArrayList<Fragment>();
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();

        manager = getSupportFragmentManager();
        FragmentTransaction f1 = manager.beginTransaction();
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
