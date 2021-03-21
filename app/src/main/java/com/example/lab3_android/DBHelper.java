package com.example.lab3_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static  final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "classmatesDB";
    public static final String TABLE_CLASSMATES = "classmates";

    public static final String KEY_ID = "_id";
    public static final String KEY_FULL_NAME = "_name";
    public static final String KEY_DATE = "_date";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

         //TODO: drop table and create new?
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_CLASSMATES+ " ("
                +" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FULL_NAME + " TEXT," + KEY_DATE + " TEXT " + ") " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSMATES);

        onCreate(db);
    }



}
