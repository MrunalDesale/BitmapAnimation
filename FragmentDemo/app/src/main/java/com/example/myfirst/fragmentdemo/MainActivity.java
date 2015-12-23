package com.example.myfirst.fragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linLayout=new LinearLayout(this);
        linLayout.setId(100);
        setContentView(linLayout);
        MyFragment frag1=new MyFragment();
        android.app.FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction ft=fm.beginTransaction();
        ft.add(100,frag1);
        ft.commit();
    }
}
