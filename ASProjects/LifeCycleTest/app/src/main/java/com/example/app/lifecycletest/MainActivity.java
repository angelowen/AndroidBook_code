package com.example.app.lifecycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "LifeCycleTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }
    protected void onStart () {
        Log.v(TAG, "onStart!");
        super.onStart();
    }
    protected void onRestart() {
        Log.v(TAG, "onRestart!");
        super.onRestart();
    }
    protected void onResume () {
        Log.v(TAG, "onResume!");
        super.onResume();
    }
    protected void onStop () {
        Log.v(TAG, "onStop!");
        super.onStop();
    }
    protected void onPause() {
        Log.v(TAG, "onPause!");
        super.onPause();
    }
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

}
