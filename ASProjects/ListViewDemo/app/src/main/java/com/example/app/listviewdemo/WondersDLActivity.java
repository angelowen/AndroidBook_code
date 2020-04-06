package com.example.app.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WondersDLActivity extends AppCompatActivity {

    String[] wondersName;
    String[] wondersURL;
    LinearLayout ll;
    ListView lv;
    Button btn;
    ArrayList<String> alst;
    ArrayList<String> alsturl;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        lv = new ListView(this);

        Intent it = getIntent();
        int sty = it.getIntExtra("style", 0);

        boolean tf = it.getBooleanExtra("textFilter", false);

        if(tf)
        {
            edt = new EditText(this);
            edt.setSingleLine();
            edt.setImeOptions(EditorInfo.IME_ACTION_DONE);
            ll.addView(edt, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            lv.setTextFilterEnabled(true);

            edt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    lv.setFilterText(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        int[] style = {
            android.R.layout.simple_list_item_1,
            android.R.layout.simple_list_item_single_choice,
            android.R.layout.simple_list_item_multiple_choice,
            android.R.layout.simple_list_item_checked,
            android.R.layout.simple_list_item_2
        };

        wondersName = getResources().getStringArray(R.array.wonderslist);
        wondersURL = getResources().getStringArray(R.array.wondersURLlist);

        alst = new ArrayList<String>();
        alst.addAll(Arrays.asList(wondersName));
        alsturl = new ArrayList<String>();
        alsturl.addAll(Arrays.asList(wondersURL));

        if(sty == 4) {
            ArrayList<HashMap<String, String>> items =new ArrayList<HashMap<String, String>>();
            for(int i = 0; i < wondersName.length; i++) {
                HashMap<String, String> item =new HashMap<String, String>();
                item.put("deptname", wondersName[i]);
                item.put("url", wondersURL[i]);
                items.add(item);
            }

            SimpleAdapter sadt = new SimpleAdapter(this, items, style[4],
                    new String[]{"deptname", "url"}, new int[]{android.R.id.text1, android.R.id.text2});
            lv.setAdapter(sadt);
        }
        else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, style[sty], alst);
            lv.setAdapter(adapter);
        }

        ll.addView(lv, new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(ll);

        switch (sty)
        {
            case 0:
                lv.setOnItemClickListener(new AdapterView
                        .OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent it = new Intent(WondersDLActivity
                                .this, DetailedWActivity.class);
                        it.putExtra("wondersName", wondersName[position]);
                        it.putExtra("wondersURL", wondersURL[position]);
                        startActivity(it);
                    }
                });
                break;

            case 1:
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                btn = new Button(this);
                btn.setText("Show me the website");
                ll.addView(btn, new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = lv.getCheckedItemPosition();
                        Intent it = new Intent(WondersDLActivity
                                .this, DetailedWActivity.class);
                        it.putExtra("wondersName", wondersName[position]);
                        it.putExtra("wondersURL", wondersURL[position]);
                        startActivity(it);
                    }
                });
                break;

            case 2:
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                Button btnDel = new Button(this);
                btnDel.setText("delete from the list");
                Button btnView = new Button(this);
                btnView.setText("view details");

                LinearLayout ll2 = new LinearLayout(this);
                ll2.setOrientation(LinearLayout.HORIZONTAL);
                ll2.addView(btnView, new LayoutParams(0,LayoutParams.WRAP_CONTENT, 1));
                ll2.addView(btnDel, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
                ll.addView(ll2, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

                btnDel.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayAdapter<String> adt = (ArrayAdapter<String>)lv.getAdapter();
                                SparseBooleanArray checked = lv.getCheckedItemPositions();

                                ArrayList<String> str2remove = new ArrayList<String>();
                                for(int i = 0; i < checked.size(); i++) {
                                    int p = checked.keyAt(i);
                                    if(checked.valueAt(i)) {
                                        String str = alst.get(p);
                                        str2remove.add(alst.get(p));
                                        lv.setItemChecked(p, false);
                                    }
                                }

                                for(String s: str2remove) {
                                    alsturl.remove(alst.indexOf(s));
                                    adt.remove(s);
                                }
                                adt.notifyDataSetChanged();
                            }
                        });

                btnView.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SparseBooleanArray checked = lv.getCheckedItemPositions();
                                int checkedCount = 0;
                                int position = 0;
                                for(int i = 0; i < checked.size(); i++) {
                                    if(checked.valueAt(i)) {
                                        checkedCount++;
                                        position = checked.keyAt(i);
                                    }
                                }
                                if(checkedCount == 1) {
                                    Intent it =new Intent(WondersDLActivity.this,
                                                    DetailedWActivity.class);
                                    it.putExtra("wondersName",alst.get(position));
                                    it.putExtra("wondersURL",alsturl.get(position));
                                    startActivity(it);
                                }
                                else {
                                    Toast.makeText(WondersDLActivity.this,
                                            "Please select exactly one item",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;

            case 3:
                lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                btn = new Button(this);
                btn.setText("Show me the website");
                ll.addView(btn, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = lv.getCheckedItemPosition();
                        Intent it = new Intent(WondersDLActivity.this,
                                DetailedWActivity.class);
                        it.putExtra("wondersName", wondersName[position]);
                        it.putExtra("wondersURL", wondersURL[position]);
                        startActivity(it);
                    }
                });
                break;

            case 4:
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                        Intent it = new Intent(WondersDLActivity.this,
                                DetailedWActivity.class);
                        it.putExtra("wondersName", wondersName[position]);
                        it.putExtra("wondersURL", wondersURL[position]);
                        startActivity(it);
                    }
                });
                break;
        }
    }

}
