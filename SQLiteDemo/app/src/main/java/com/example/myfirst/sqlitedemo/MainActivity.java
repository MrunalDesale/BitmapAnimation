package com.example.myfirst.sqlitedemo;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    DatabaseHelper mydb;
    EditText editName,editClass,editMarks,editId;
    Button buttonnAdd;
    Button buttonViewAll;
    Button buttonUpdate;
    Button buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        editName=(EditText)findViewById(R.id.nameText);
        editClass=(EditText)findViewById(R.id.classText);
        editMarks=(EditText)findViewById(R.id.marksText);
        editId=(EditText)findViewById(R.id.editId);

        buttonnAdd=(Button)findViewById(R.id.buttonAdd);
        buttonViewAll=(Button)findViewById(R.id.buttonViewAll);
        buttonUpdate=(Button)findViewById(R.id.buttonUpdate);
        buttonDelete=(Button)findViewById(R.id.buttonDelete);
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
    }

    public void AddData()
    {
        buttonnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted= mydb.insertData(editName.getText().toString(),editClass.getText().toString(),editMarks.getText().toString());
                        if(isInserted==true)
                            Toast.makeText(MainActivity.this,"Data inserted successfuly",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void ViewAll()
    {
        buttonViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= mydb.getAllData();

                        if(res.getCount() == 0)
                        {
                            ShowMessage("Error","Nothing to display");
                            return;
                        }
                        else
                        {
                            StringBuffer buffer=new StringBuffer();
                            while (res.moveToNext())
                            {
                                buffer.append("ID: "+res.getString(0)+"\n");
                                buffer.append("Name: "+res.getString(1)+"\n");
                                buffer.append("Class: "+res.getString(2)+"\n");
                                buffer.append("Marks: "+res.getString(3)+"\n\n");
                            }
                            ShowMessage("Data",buffer.toString());
                        }
                    }
                }
        );
    }
    public void UpdateData()
    {
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated=mydb.UpdateData(editId.getText().toString(),editName.getText().toString(),
                                editClass.getText().toString(),editMarks.getText().toString());

                        if(isUpdated == true)

                            Toast.makeText(MainActivity.this,"Data updated successfuly",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void DeleteData()
    {
        buttonDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRow=mydb.DeleteData(editId.getText().toString());

                        if (deletedRow > 0)
                            Toast.makeText(MainActivity.this,"Data deleted successfuly",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void ShowMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
