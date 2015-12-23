package com.example.myfirst.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Date;
import java.text.DateFormat;

/**
 * Created by bridgelabz3 on 22/12/15.
 */
public class MyServices extends Service
{
    String startTime,endTime;
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    public int onStartCommand(Intent intent,int flag,int startId)
    {
        startTime= DateFormat.getDateTimeInstance().format(new Date());
        Toast.makeText(this,""+startTime,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Service started.",Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
    public void onDestroy()
    {
        endTime= DateFormat.getDateTimeInstance().format(new Date());
        Toast.makeText(this,""+endTime,Toast.LENGTH_LONG).show();

        super.onDestroy();
        Toast.makeText(this,"Service destroyed",Toast.LENGTH_LONG).show();

    }
}
