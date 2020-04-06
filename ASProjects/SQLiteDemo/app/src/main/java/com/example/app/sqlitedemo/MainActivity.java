package com.example.app.sqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        SQLiteDatabase db = new MySQLiteOpenHelper(this).getReadableDatabase();

        String sql="select " +
                MySQLiteOpenHelper.CONTACT_ID + ","+
                MySQLiteOpenHelper.CONTACT_NAME + "," +
                MySQLiteOpenHelper.CONTACT_Phone + " from " +
                MySQLiteOpenHelper.CONTACT_TABLE;

        Cursor cursor = db.rawQuery(sql, null);

        final ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

        if(cursor.getCount() != 0) {
            cursor.moveToLast();
            for(int i=0; i<cursor.getCount(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put(MySQLiteOpenHelper.CONTACT_ID, cursor.getString(0));
                map.put(MySQLiteOpenHelper.CONTACT_NAME, cursor.getString(1));
                map.put(MySQLiteOpenHelper.CONTACT_Phone, cursor.getString(2));
                list.add(map);
                cursor.moveToPrevious();
            }
        }
        cursor.close();
        db.close();

        SimpleAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2,
                new String[]{MySQLiteOpenHelper.CONTACT_NAME,
                        MySQLiteOpenHelper.CONTACT_Phone},
                new int[]{android.R.id.text1, android.R.id.text2});

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("id", list.get(position)
                        .get(MySQLiteOpenHelper.CONTACT_ID));
                intent.putExtra("name", list.get(position)
                        .get(MySQLiteOpenHelper.CONTACT_NAME));
                intent.putExtra("phone", list.get(position)
                        .get(MySQLiteOpenHelper.CONTACT_Phone));
                intent.setClass(MainActivity.this, ModifyContactActivity.class);
                startActivity(intent);
            }
        });

    }
    public void actionPerformed(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AddAContactActivity.class);
        startActivity(intent);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
