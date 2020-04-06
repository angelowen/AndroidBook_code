package com.example.app.uitest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
    }
    public void sendString(View view) {
        Intent intent = new Intent(this, DisplayStringActivity.class);
        EditText editText = (EditText) findViewById(R.id.inputString);
        String string2send = editText.getText().toString();
        intent.putExtra("userInputtedString", string2send);
        startActivity(intent);

    }

}
