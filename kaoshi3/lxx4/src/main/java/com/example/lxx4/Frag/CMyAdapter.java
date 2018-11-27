package com.example.lxx4.Frag;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lxx4.R;

import java.util.ArrayList;

public class CMyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> clist;
    private TextView tv11;

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

        convertView = View.inflate(context,R.layout.citem,null);
        tv11 = convertView.findViewById(R.id.tv11);
        tv11.setText(clist.get(position).toString());

        return convertView;
    }
}
