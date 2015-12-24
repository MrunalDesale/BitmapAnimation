package com.example.myfirst.broadcastdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity
{
    Button buttonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCall=(Button)findViewById(R.id.buttonCall);

        buttonCall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent=new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:5556"));
                        startActivity(callIntent);
                    }
                }
        );
    }
}
