package com.example.myfirst.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bridgelabz3 on 23/12/15.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Student_Table";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Class";
    public static final String COL_4="Marks";
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT, Class TEXT, Marks INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String Class,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,Class);
        contentValues.put(COL_4,marks);
        long res= db.insert(TABLE_NAME,null,contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        //Cursor return random read/write access to db
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean UpdateData(String id,String name,String Class,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,Class);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME,contentValues,"id = ?",new String[] { id });
        return true;
    }
    public Integer DeleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ? ",new String[]{id});
    }
}
