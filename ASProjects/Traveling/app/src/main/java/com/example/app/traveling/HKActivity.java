package com.example.app.traveling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
