package com.example.myfirst.sendsms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText editNumber, editMessage;
    ImageButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = (EditText) findViewById(R.id.editNumber);
        editMessage = (EditText) findViewById(R.id.editMessage);
        buttonSend=(ImageButton)findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendSms();
                    }
                }
        );
    }

    private void sendSms() {
        String number = editNumber.getText().toString();
        String message = editMessage.getText().toString();

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, null, null);

        Toast.makeText(getApplicationContext(), "Message sent successfuly", Toast.LENGTH_LONG).show();
    }
}