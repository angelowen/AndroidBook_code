package com.example.app.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {
    ListView listView;
    String[] list = {"Paris", "Zurich", "HongKong", "Beijing"};
    ArrayAdapter<String> listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_menu, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        switch (position)
                        {
                            case 0:
                                ParisFragment paris = new ParisFragment();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.frameB, paris).commit();
                                break;
                            case 1:
                                ZurichFragment zurich = new ZurichFragment();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.frameB, zurich).commit();
                                break;
                            case 2:
                                HKFragment hk = new HKFragment();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.frameB, hk).commit();
                                break;
                            case 3:
                                BeijingFragment beijing = new BeijingFragment();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.frameB, beijing).commit();
                                break;
                        }
                    }
                });
        return view;
    }

}
