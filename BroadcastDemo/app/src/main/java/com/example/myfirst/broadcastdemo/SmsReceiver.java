package com.example.myfirst.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by bridgelabz3 on 24/12/15.
 */
public class SmsReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle=intent.getExtras();
        if(bundle != null)
        {
            Object[] pdus=(Object[])bundle.get("pdus");

            String senderNumber=null;
            for (int i=0;i<pdus.length;i++)
            {
                SmsMessage sms=SmsMessage.createFromPdu((byte[]) pdus[i]);

                senderNumber=sms.getOriginatingAddress();
                String message=sms.getDisplayMessageBody();

                Toast.makeText(context,"From: "+senderNumber+" Message: "+message,Toast.LENGTH_LONG).show();
            }
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(senderNumber,null,"Hi. I will call you later",null,null);

        }
    }
}
