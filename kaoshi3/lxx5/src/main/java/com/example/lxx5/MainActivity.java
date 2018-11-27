package com.example.lxx5;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    int count=3;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                if(count<0){
                    Intent intent = new Intent(MainActivity.this,TwoActivity3.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }

            tv.setText(count--+"秒");
            handler.sendEmptyMessageDelayed(200,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        handler.sendEmptyMessage(200);



    }
}
