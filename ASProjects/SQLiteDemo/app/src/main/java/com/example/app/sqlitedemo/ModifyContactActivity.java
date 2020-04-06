package com.example.app.sqlitedemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifyContactActivity extends AppCompatActivity {

    String id;
    EditText textName;
    EditText textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modify_contact);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id", "0");
        textName = (EditText) findViewById(R.id.edtName);
        textPhone = (EditText) findViewById(R.id.edtPhone);
        textName.setText(bundle.getString("name"));
        textPhone.setText(bundle.getString("phone"));
    }

    public void actionPerformed(View view)
    {
        SQLiteDatabase db;
        switch (view.getId())
        {
            case R.id.btnModify:
                db = new MySQLiteOpenHelper(this).getWritableDatabase();
                String sqlupdate="update " +
                        MySQLiteOpenHelper.CONTACT_TABLE +
                        " set " + MySQLiteOpenHelper.CONTACT_NAME +
                        "='" + textName.getText().toString() + "', " +
                        MySQLiteOpenHelper.CONTACT_Phone + "='" +
                        textPhone.getText().toString() +"' " +
                        "where " + MySQLiteOpenHelper.CONTACT_ID +
                        "=" + id;
                db.execSQL(sqlupdate);
                db.close();
                break;

            case R.id.btnDelete:
                db = new MySQLiteOpenHelper(this).getWritableDatabase();
                String sqldel = "delete from " +
                        MySQLiteOpenHelper.CONTACT_TABLE +
                        " where " + MySQLiteOpenHelper.CONTACT_ID + "=" +
                        id;
                db.execSQL(sqldel);
                db.close();
                break;
        }
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
