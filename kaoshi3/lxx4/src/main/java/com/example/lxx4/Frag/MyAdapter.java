package com.example.lxx4.Frag;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxx4.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Bean.DataBean> data;
    private TextView tv1;
    private TextView tv2;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

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
        return position % 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        int viewType = getItemViewType(position);
        switch (viewType){
            case 0:
                convertView = View.inflate(context, R.layout.even,null);
                tv1 = convertView.findViewById(R.id.tv1);
                tv2 = convertView.findViewById(R.id.tv2);
                tv1.setText(data.get(position).getNews_title());
                tv2.setText(data.get(position).getNews_summary());
                break;
            case 1:
                convertView = View.inflate(context,R.layout.add,null);
                iv1 = convertView.findViewById(R.id.iv1);
                iv2 = convertView.findViewById(R.id.iv2);
                iv3 = convertView.findViewById(R.id.iv3);
                ImageLoader instance = ImageLoader.getInstance();
                instance.displayImage(data.get(position).getPic_url(),iv1);
                instance.displayImage(data.get(position).getPic_url(),iv2);
                instance.displayImage(data.get(position).getPic_url(),iv3);
                break;

        }
        return convertView;
    }
}
