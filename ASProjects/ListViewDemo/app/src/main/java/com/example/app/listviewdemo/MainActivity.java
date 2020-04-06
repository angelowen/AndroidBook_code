package com.example.app.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        findViewById(R.id.btnLV2).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, WondersDLActivity.class);
                it.putExtra("style", 0);
                startActivity(it);
            }
        });

        findViewById(R.id.btnLV3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, WondersDLActivity.class);
                it.putExtra("style", 1);
                startActivity(it);
            }
        });
        findViewById(R.id.btnLV4).setOnClickListener(this);
        findViewById(R.id.btnLV5).setOnClickListener(this);
        findViewById(R.id.btnLV6).setOnClickListener(this);
        findViewById(R.id.btnLV7).setOnClickListener(this);
        findViewById(R.id.btnLV8).setOnClickListener(this);
    }

    public void onClickListView1 (View view) {
        Intent it = new Intent(this, WondersActivity.class);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLV4: {
                Intent it = new Intent(MainActivity.this,
                        WondersDLActivity.class);
                it.putExtra("style", 2);
                startActivity(it);
                break;
            }

            case R.id.btnLV5:{
                Intent it = new Intent(MainActivity.this,
                        WondersDLActivity.class);
                it.putExtra("style", 3);
                startActivity(it);
                break;
            }

            case R.id.btnLV6:{
                Intent it = new Intent(MainActivity.this,
                        WondersDLActivity.class);
                it.putExtra("style", 4);
                startActivity(it);
                break;
            }

            case R.id.btnLV7:{
                Intent it = new Intent(MainActivity.this, WondersDLActivity.class);
                it.putExtra("style", 0);
                it.putExtra("textFilter", true);
                startActivity(it);
                break;
            }

            case R.id.btnLV8:{
                Intent it = new Intent(MainActivity.this,
                        MyListViewActivity.class);
                startActivity(it);
                break;
            }

        }
    }

}
