package com.example.myfirst.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    String[] MoblieList={"Nokia","Samsung","Lenovo","Sony","Micromax"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.activity_listview,MoblieList);
        ListView listView=(ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }
}
