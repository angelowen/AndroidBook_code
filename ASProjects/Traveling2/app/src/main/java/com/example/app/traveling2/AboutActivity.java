package com.example.app.traveling2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);
    }

    public void actionPerformed(View view) {
        Intent intent = new Intent();
        switch(view.getId())
        {
            case R.id.emailMe:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:someone@somewhere.com "));
                intent.putExtra(Intent.EXTRA_SUBJECT,
                        "[About Traveling]");
                startActivity(intent);
                break;
            case R.id.visitHomepage:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://junwu.nptu.edu.tw "));
                startActivity(intent);
                break;
            case R.id.callMe:
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:087663800"));
                startActivity(intent);
                break;
        }
    }

}
