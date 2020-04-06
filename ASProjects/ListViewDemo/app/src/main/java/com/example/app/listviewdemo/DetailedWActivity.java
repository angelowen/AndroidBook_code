package com.example.app.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailedWActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailed_w);

        wv = (WebView) findViewById(R.id.wondersWebView);
        wv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wv.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);

        String deptName = getIntent().getStringExtra("wondersName");
        String deptURL = getIntent().getStringExtra("wondersURL");
        getSupportActionBar().setTitle(deptName);
        wv.loadUrl(deptURL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode == KeyEvent.KEYCODE_BACK) && (wv.canGoBack()))
        {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
