package com.example.myfirst.navdrawdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by bridgelabz3 on 5/1/16.
 */
public class Home extends Activity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv=(TextView)findViewById(R.id.textView1);
        tv.setText("You are in Home activity");
    }
}