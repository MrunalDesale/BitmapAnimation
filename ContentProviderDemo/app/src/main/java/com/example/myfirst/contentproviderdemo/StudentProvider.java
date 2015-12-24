package com.example.myfirst.contentproviderdemo;
//import android.cursor.dir/vnd.example.students;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by bridgelabz3 on 24/12/15.
 */
public class StudentProvider extends ContentProvider
{

    static final String PROVIDER_NAME="com.example.myfirst.contentproviderdemo.StudentProvider";
    static final String URL="content://"+PROVIDER_NAME+"/students";
    static final Uri CONTENT_URI=Uri.parse(URL);

    static final String ID="id";
    static final String NAME="name";
    static final String GRADE="grade";

    private static HashMap<String, String> STUDENT_PROJECTION_MAP;
    static final int STUDENTS=1;
    static final int STUDENTS_ID=2;

    static final UriMatcher uriMatcher;
    static
    {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"students",STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME,"students/#",STUDENTS_ID);
    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME="College";
    static final String STUDENT_TABLE_NAME="students";
    static final int DATABASE_VERSION=1;
    static final String CREATE_DB_TABLE="CREATE TABLE "+STUDENT_TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "name TEXT NOT NULL, "+"grade TEXT NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+STUDENT_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate()
    {
        Context context=getContext();
        DatabaseHelper dbHelper=new DatabaseHelper(context);
        db=dbHelper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(STUDENT_TABLE_NAME);

        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                qb.setProjectionMap(STUDENT_PROJECTION_MAP);
                break;
            case STUDENTS_ID:
                qb.appendWhere(ID+ "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
        if(sortOrder == null || sortOrder == "")
        {
            sortOrder=NAME;
        }
        Cursor c=qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public String getType(Uri uri)
    {
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";

            case STUDENTS_ID:
                return "vnd.android.cursor.item/vnd.example.students";

            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        long rowId=db.insert(STUDENT_TABLE_NAME,"",values);

        if(rowId > 0)
        {
            Uri uri1= ContentUris.withAppendedId(CONTENT_URI,rowId);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new SQLException("Fail to add record into "+uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        int count=0;

        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                count=db.delete(STUDENT_TABLE_NAME,selection,selectionArgs);
                break;
            case STUDENTS_ID:
                String id=uri.getPathSegments().get(1);
                count=db.delete(STUDENT_TABLE_NAME,ID+ " = "+id+(!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count=0;

        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                count=db.update(STUDENT_TABLE_NAME,values,selection,selectionArgs);
                break;
            case STUDENTS_ID:
                count=db.update(STUDENT_TABLE_NAME,values,ID+ " = "+uri.getPathSegments().get(1)+(!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;

    }
}
