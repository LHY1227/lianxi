package com.example.lxx5.cela;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lxx5.R;

import java.util.ArrayList;

public class CMyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> clist;
    private TextView ctv;

    public CMyAdapter(Context context, ArrayList<String> clist) {
        this.context = context;
        this.clist = clist;
    }

    @Override
    public int getCount() {
        return clist.size();
    }

    @Override
    public Object getItem(int position) {
        return clist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context, R.layout.citem,null);
        ctv = convertView.findViewById(R.id.ctv);
        ctv.setText(clist.get(position).toString());
        return convertView;
    }
}
