package com.example.day20rk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Bean.DataBean> data;
    private ImageView iv;
    private TextView tv;

    public MyAdapter(Context context, List<Bean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView  = View.inflate(context,R.layout.item,null);
        iv = convertView.findViewById(R.id.iv);
        tv = convertView.findViewById(R.id.tv);

        tv.setText(data.get(position).getNews_title());

        return convertView;
    }
}
