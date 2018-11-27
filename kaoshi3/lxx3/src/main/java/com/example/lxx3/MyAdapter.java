package com.example.lxx3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Bean.DataBean> data;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView iv1;

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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType = getItemViewType(position);
        switch (viewType){
            case 0:
                convertView = View.inflate(context,R.layout.even,null);
                tv1 = convertView.findViewById(R.id.tv1);
                tv2 = convertView.findViewById(R.id.tv2);
                tv1.setText(data.get(position).getNews_id());
                tv2.setText(data.get(position).getNews_title());
                break;
            case 1:
                convertView = View.inflate(context,R.layout.add,null);
                tv3 = convertView.findViewById(R.id.tv3);
                iv1 = convertView.findViewById(R.id.iv1);
                tv3.setText(data.get(position).getNews_title());
                ImageLoader instance = ImageLoader.getInstance();
                instance.displayImage(data.get(position).getPic_url(),iv1);
                break;
        }

        return convertView;
    }
}
