package com.example.myfirst.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bridgelabz3 on 19/12/15.
 */
public class MyFragment extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout linLayout =new LinearLayout(getActivity());
        Button b=new Button(getActivity());
        b.setText("Hello Button");
        linLayout.addView(b);

        final TextView tv=new TextView(getActivity());
        tv.setText("Text Entry");
        tv.setId(110);
        linLayout.addView(tv);


        View.OnClickListener onclick=new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Button b=(Button)v;
                tv.setText(b.getText());
            }
        };
        b.setOnClickListener(onclick);
        if(savedInstanceState != null)
        {
            tv.setText(savedInstanceState.getCharSequence("My Text"));
        }
        return linLayout;
    }
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        TextView tv1=(TextView)getView().findViewById(110);
        savedInstanceState.putCharSequence("myText",tv1.getText());
    }

}
