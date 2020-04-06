package com.example.app.traveling2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ParisActivity extends AppCompatActivity {

    private static boolean isLike=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_paris);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Boolean isDesp = getIntent().getBooleanExtra("isDescription", true);
        String optText = getIntent().getStringExtra("optionalText");
        int fs = getIntent().getIntExtra("optionalFontSize", 24);

        TextView tv = (TextView) findViewById(R.id.parisDesp);
        TextView ot = (TextView) findViewById(R.id.parisOptText);
        TextView it = (TextView) findViewById(R.id.likeText);

        it.setText(null);

        if (isDesp) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fs);
            tv.setVisibility(View.VISIBLE);
            ot.setText(optText);
            ot.setTextSize(TypedValue.COMPLEX_UNIT_SP, fs);
            ot.setVisibility(View.VISIBLE);
        }
        else {
            tv.setVisibility(View.INVISIBLE);
            ot.setVisibility(View.INVISIBLE);
        }
        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.likeFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) ParisActivity.this.
                        findViewById(R.id.likeText);
                if (!isLike) {
                    tv.setText("I like it!");
                    isLike=true;
                } else {
                    tv.setText("");
                    isLike = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isLike", isLike);

        Intent mIntent = new Intent();
        mIntent.putExtras(bundle);

        setResult(RESULT_OK, mIntent);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home)
        {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isLike", isLike);

            Intent mIntent = new Intent();
            mIntent.putExtras(bundle);

            setResult(RESULT_OK, mIntent);
            finish();
            return true;
        }

        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
