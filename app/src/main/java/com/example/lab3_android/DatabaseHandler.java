package com.example.lab3_android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.RequiresApi;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DatabaseHandler {

    private static SQLiteDatabase _sqLiteDatabase;
    private  DBHelper _dbHelper;
    private  Date _dateTime;


    public DatabaseHandler(DBHelper dbHelper){
        _dbHelper = dbHelper;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertData(EditText addName, EditText addId){

        _sqLiteDatabase = _dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String full_name = addName.getText().toString();
        String id = addId.getText().toString();

        _dateTime = new Date();
        String utcTime = DateTimeHandler.localTimeToUtc(_dateTime);

        contentValues.put(DBHelper.KEY_FULL_NAME, full_name);
        contentValues.put(DBHelper.KEY_ID, id);
        contentValues.put(DBHelper.KEY_DATE, utcTime);

        _sqLiteDatabase.insert(DBHelper.TABLE_CLASSMATES, null, contentValues);
    }


    public static void readData(DBHelper dbHelper) throws ParseException {
        _sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = _sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null,null,null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FULL_NAME);
            int timeIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);


            do {

                String utcTime = cursor.getString(timeIndex);
                Date utcToLocal = DateTimeHandler.utcToLocalTime(utcTime);
                String localTime = DateTimeHandler.getDateFormat(utcToLocal);
                    Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                            ", name = " + cursor.getString(nameIndex) +
                            ", time = " + localTime);
            }while (cursor.moveToNext());

        } else
            Log.d("mLog","0 rows");

        cursor.close();
    }

    public void deleteData(){
        _sqLiteDatabase.delete(DBHelper.TABLE_CLASSMATES, null, null);
    }

}
