package com.example.app.listviewdemo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<MyListItem> itemList;

    MyListAdapter(Activity activity, ArrayList<MyListItem> itemList) {
        this.activity = activity;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = activity.getLayoutInflater().inflate(R.layout.layout_mylist_item, null);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tv1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView2);
        img.setImageResource(itemList.get(position).getImg());

        String title = itemList.get(position).getWonders();
        tv1.setText(title);
        tv2.setText(itemList.get(position).getWondersURL());

        return convertView;
    }

}
