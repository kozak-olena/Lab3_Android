package com.example.lab3_android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.RequiresApi;


import java.text.ParseException;
import java.util.Date;

public class DatabaseHandler {

    public static SQLiteDatabase sqLiteDatabase;
    public static  DBHelper dbHelper;
    private  Date _dateTime;


    public DatabaseHandler(DBHelper dbHelper){
        DatabaseHandler.dbHelper = dbHelper;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertData(EditText addName, EditText addId){

        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String full_name = addName.getText().toString();
        /*String id = addId.getText().toString();*/

        _dateTime = new Date();
        long utcTime = DateTimeHandler.localTimeToUtc(_dateTime);

        contentValues.put(DBHelper.KEY_FULL_NAME, full_name);
        /*contentValues.put(DBHelper.KEY_ID, id);*/
        contentValues.put(DBHelper.KEY_DATE, utcTime);

        sqLiteDatabase.insert(DBHelper.TABLE_CLASSMATES, null, contentValues);
    }

    public void updateLastRecord() throws ParseException {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_FULL_NAME, "Іванов Іван Іванович");
        String orderBy = DBHelper.KEY_DATE+" ASC ";
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null, null,
                null, null, orderBy);
        cursor.moveToLast();


        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FULL_NAME);
        int timeIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);

        int updCount = sqLiteDatabase.update(DBHelper.TABLE_CLASSMATES, contentValues,
                DBHelper.KEY_DATE + "= ?", new String[] {cursor.getString(timeIndex)});

        String str = cursor.getString(timeIndex);       //????? чого тут стрінг?
        Date date = DateTimeHandler.utcToLocalTime(Long.parseLong(str));
        String localTime = DateTimeHandler.getDateFormat(date);

        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                ", name = " + cursor.getString(nameIndex) +
                ", time = " + localTime);
    }


    public void readData(DBHelper dbHelper) throws ParseException {

        sqLiteDatabase = dbHelper.getWritableDatabase();

       /* Cursor cursor1 = _sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null, null, null, null, null);*/

        String orderBy = DBHelper.KEY_DATE+" ASC ";

        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null, null,
                null, null, orderBy);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FULL_NAME);
            int timeIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);


         do {
                String str = cursor.getString(timeIndex);       //????? чого тут стрінг?
                Date date = DateTimeHandler.utcToLocalTime(Long.parseLong(str));
                String localTime = DateTimeHandler.getDateFormat(date);


                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                            ", name = " + cursor.getString(nameIndex) +
                            ", time = " + localTime);
            }while (cursor.moveToNext());

        } else
            Log.d("mLog", "0 rows");

            cursor.close();
        }



   /* public void deleteData(){
        _sqLiteDatabase.delete(DBHelper.TABLE_CLASSMATES, null, null);
    }*/

}
