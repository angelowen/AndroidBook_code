package com.example.app.traveling2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int likecount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_action_tw);

        NumberPicker np=(NumberPicker)findViewById(R.id.numberPicker);
        np.setEnabled(false);
        np.setMaxValue(48);
        np.setMinValue(12);
        np.setValue(24);

        CheckBox cb = (CheckBox)findViewById(R.id.cbDescription);
        cb.setOnCheckedChangeListener(new
        CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                EditText et = (EditText) MainActivity.this.
                          findViewById(R.id. optText);
                NumberPicker np = (NumberPicker) MainActivity.this.findViewById(R.id.numberPicker);
                if (isChecked) {
                    et.setEnabled(true);
                    np.setEnabled(true);
                } else {
                    et.setEnabled(false);
                    np.setEnabled(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent mIntent = new Intent(this, AboutActivity.class);
            startActivity(mIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void actionPerformed(View view) {
        Intent intent = new Intent();

        CheckBox cbDesp = (CheckBox)findViewById(R.id.cbDescription);
        EditText et=(EditText)findViewById(R.id.optText);
        NumberPicker np=(NumberPicker) findViewById(R.id.numberPicker);

        switch(view.getId())
        {
            case R.id.btnParis:
                intent.setClass(this, ParisActivity.class);
                intent.putExtra("isDescription", cbDesp.isChecked());
                intent.putExtra("optionalText",et.getText().toString());
                intent.putExtra("optionalFontSize", np.getValue());
                startActivityForResult(intent, 100);
                break;
            case R.id.btnZurich:
                intent.setClass(this, ZurichActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAsiaCities:
                intent.setAction("asia.city");
                intent = Intent.createChooser(intent, "Please pick a city");
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        CheckBox cb = (CheckBox)findViewById(R.id.cbDescription);
        EditText et=(EditText)findViewById(R.id.optText);
        NumberPicker np=(NumberPicker)findViewById(R.id.numberPicker);
        prefsEditor.putBoolean("cbvalue", cb.isChecked());
        prefsEditor.putString("opttextvalue", et.getText().toString());
        prefsEditor.putInt("npvalue", np.getValue());
        prefsEditor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());

        CheckBox cb = (CheckBox)findViewById(R.id.cbDescription);
        EditText et=(EditText)findViewById(R.id.optText);
        NumberPicker np=(NumberPicker)findViewById(R.id.numberPicker);

        Boolean cbChecked = appSharedPrefs.getBoolean("cbvalue", false);
        String optext = appSharedPrefs.getString("opttextvalue","");
        int npv = appSharedPrefs.getInt("npvalue", 24);

        if(cbChecked) {
            cb.setChecked(true);
            et.setEnabled(true);
            np.setEnabled(true);
            et.setText(optext);
            np.setValue(npv);
        }
        else {
            cb.setChecked(false);
            et.setEnabled(false);
            np.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            boolean isLike= data.getBooleanExtra("isLike",false);
            if(isLike)
                likecount++;
            TextView tv = (TextView)MainActivity.this.
                    findViewById(R.id.likeCount);
            tv.setText(""+likecount);
        }
    }

}
