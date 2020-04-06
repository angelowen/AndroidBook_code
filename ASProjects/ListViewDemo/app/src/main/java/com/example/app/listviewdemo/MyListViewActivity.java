package com.example.app.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;

public class MyListViewActivity extends AppCompatActivity {

    String[] wondersURL;
    String[] wondersName;
    LinearLayout ll;
    ListView lv;
    ArrayList<String> alst;
    ArrayList<String> alsturl;
    ArrayList<MyListItem> itemList = new ArrayList<MyListItem>();
    MyListAdapter madt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        lv = new ListView(this);

        wondersName = getResources().getStringArray(R.array.wonderslist);
        wondersURL = getResources().getStringArray(R.array.wondersURLlist);

        for(int i = 0; i < wondersName.length; i++) {
            MyListItem item = new MyListItem();
            item.setWonders(wondersName[i]);
            item.setWondersURL(wondersURL[i]);
            item.setImg(android.R.drawable.btn_star_big_on);
            itemList.add(item);
        }

        madt = new MyListAdapter(this, itemList);
        lv.setAdapter(madt);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MyListViewActivity.this, DetailedWActivity.class);
                it.putExtra("wondersName", wondersName[position]);
                it.putExtra("wondersURL", wondersURL[position]);
                startActivity(it);
            }
        });

        ll.addView(lv, new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(ll);
    }
}
