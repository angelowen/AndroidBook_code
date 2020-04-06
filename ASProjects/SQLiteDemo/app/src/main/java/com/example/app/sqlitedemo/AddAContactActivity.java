package com.example.app.sqlitedemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddAContactActivity extends AppCompatActivity {

    TextView textName, textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_a_contact);
        textName = (TextView)findViewById(R.id.textName);
        textPhone = (TextView)findViewById(R.id.textPhone);

    }

    public void actionPerformed(View view)
    {
        Intent intent = new Intent();

        switch(view.getId())
        {
            case R.id.btnAdd:
                saveContact();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCancel:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void saveContact()
    {
        SQLiteDatabase db = new MySQLiteOpenHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.CONTACT_NAME, textName.getText().toString());
        values.put(MySQLiteOpenHelper.CONTACT_Phone, textPhone.getText().toString());
        db.insert(MySQLiteOpenHelper.CONTACT_TABLE.toString(), null, values);
        db.close();
    }


}
