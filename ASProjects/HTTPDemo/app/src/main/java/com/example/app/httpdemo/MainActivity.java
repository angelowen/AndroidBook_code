package com.example.app.httpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        findViewById(R.id.btnHttpURLConnection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, HttpURLConnectionDemoActivity.class);
                startActivity(it);
            }
        });

        findViewById(R.id.btnHeadlines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, NPTUHeadlinesActivity.class);
                startActivity(it);
            }
        });

    }
}
