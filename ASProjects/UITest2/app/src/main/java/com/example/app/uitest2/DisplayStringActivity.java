package com.example.app.uitest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String outputString = intent.getStringExtra("userInputtedString");
        setContentView(R.layout.layout_display_string);
        TextView showStringTV = (TextView) findViewById(R.id.showString);
        showStringTV.setText(outputString);

    }
}
