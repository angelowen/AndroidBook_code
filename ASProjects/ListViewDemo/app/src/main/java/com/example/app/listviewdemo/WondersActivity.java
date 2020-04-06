package com.example.app.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WondersActivity extends AppCompatActivity {

    String[] wondersName;
    String[] wondersURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wonders);

        ListView lv = (ListView)findViewById(R.id.wonderslist);

        lv.setEmptyView(findViewById(R.id.empty));

        wondersName = getResources().getStringArray(R.array.wonderslist);
        wondersURL = getResources().getStringArray(R.array.wondersURLlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, wondersName);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent it = new Intent(WondersActivity.this,DetailedWActivity.class);
                it.putExtra("wondersName", wondersName[position]);
                it.putExtra("wondersURL", wondersURL[position]);
                startActivity(it);
            }
        });
    }
}
