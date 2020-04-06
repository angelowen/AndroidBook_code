package com.example.junwu.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }

    public void actionPerformed(View view)
    {
        Intent intent = new Intent();
        switch(view.getId())
        {
            case R.id.btnImage:
                intent.setClass(this, ImageActivity.class);
                startActivity(intent);
                break;

            case R.id.btnAudio:
                intent.setClass(this, AudioActivity.class);
                startActivity(intent);
                break;

            case R.id.btnVideo:
                intent.setClass(this, VideoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
