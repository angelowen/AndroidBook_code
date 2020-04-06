package com.example.app.httpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailedHeadlinesActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailed_headlines);

        wv =(WebView) findViewById(R.id.deptWebView);
        wv.setWebViewClient(new WebViewClient());

        WebSettings wsettings= wv.getSettings();
        wsettings.setSupportZoom(true);
        wsettings.setBuiltInZoomControls(true);
        wsettings.setDisplayZoomControls(false);
        wsettings.setJavaScriptEnabled(true);
        wsettings.setJavaScriptCanOpenWindowsAutomatically(true);

        String date = getIntent().getStringExtra("date");
        String title = getIntent().getStringExtra("title");
        String poster = getIntent().getStringExtra("poster");
        String url = getIntent().getStringExtra("url");
        getSupportActionBar().setTitle("Headlines");
        wv.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) &&
                (wv.canGoBack())){
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
