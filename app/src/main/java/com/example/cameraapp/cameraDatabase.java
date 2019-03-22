package com.example.cameraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class cameraDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME="cameradb";
    private static final int DB_VERSION=1;

    cameraDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE CAMERAIMAGES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"IMAGE blob not null);");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS IMAGE");
    }
    public void insertCamera(SQLiteDatabase db,byte[] data){
        ContentValues cameraValues=new ContentValues();
        cameraValues.put("IMAGE",data);
        db.insert("CAMERAIMAGES",null, cameraValues);
    }
}

