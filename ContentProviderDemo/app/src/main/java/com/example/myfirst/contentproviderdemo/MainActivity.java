package com.example.myfirst.contentproviderdemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText editName,editGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName=(EditText)findViewById(R.id.editName);
        editGrade=(EditText)findViewById(R.id.editGrade);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickAddName(View view)
    {
        ContentValues values=new ContentValues();

        values.put(StudentProvider.NAME,editName.getText().toString());
        values.put(StudentProvider.GRADE,editGrade.getText().toString());

        Uri uri=getContentResolver().insert(StudentProvider.CONTENT_URI,values);
        Toast.makeText(getBaseContext(),uri.toString(),Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view)
    {
        String URL="content://com.example.myfirst.contentproviderdemo.StudentProvider/students";

        Uri students=Uri.parse(URL);
        Cursor c=getContentResolver().query(students,null,null, null,"name");

        if (c.moveToFirst())
        {
            do
            {
                Toast.makeText(this,c.getString(c.getColumnIndex(StudentProvider.ID))+", "
                +c.getString(c.getColumnIndex(StudentProvider.NAME))+", "
                +c.getString(c.getColumnIndex(StudentProvider.GRADE)),Toast.LENGTH_SHORT).show();
            }while (c.moveToNext());
        }
    }

    private Cursor managedQuery() {
        return null;
    }
}
