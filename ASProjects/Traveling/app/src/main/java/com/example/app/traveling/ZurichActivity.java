package com.example.app.traveling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ZurichActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_zurich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
