package com.example.lab3_android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.EditText;

import androidx.annotation.RequiresApi;


import java.util.Date;

public class DatabaseHandler {

    public static SQLiteDatabase sqLiteDatabase;
    public static DBHelper _dbHelper;
    private Date _dateTime;
    private ContentValues _contentValues;
    private Cursor _cursor;


    public DatabaseHandler(DBHelper dbHelper) {
        _dbHelper = dbHelper;

        sqLiteDatabase = _dbHelper.getWritableDatabase();

        _contentValues = new ContentValues();
    }

    public Cursor getSortedData() {
        _cursor = sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null, null,
                null, null, DBHelper.KEY_DATE + " ASC ");
        return _cursor;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)            //TODO:???
    public void insertData(EditText addName) {

        String full_name = addName.getText().toString();

        _dateTime = new Date();
        long utcTime = DateTimeHandler.localTimeToUtc(_dateTime);

        _contentValues.put(DBHelper.KEY_FULL_NAME, full_name);
        _contentValues.put(DBHelper.KEY_DATE, utcTime);

        sqLiteDatabase.insert(DBHelper.TABLE_CLASSMATES, null, _contentValues);


    }

    public void updateLastRecord() {
        _contentValues.put(DBHelper.KEY_FULL_NAME, "Іванов Іван Іванович");
        _cursor = getSortedData();

        _cursor.moveToLast();
        int timeIndex = _cursor.getColumnIndex(DBHelper.KEY_DATE);
        int updCount = sqLiteDatabase.update(DBHelper.TABLE_CLASSMATES, _contentValues,
                DBHelper.KEY_DATE + "= ?", new String[]{_cursor.getString(timeIndex)});
    }

    public Cursor readData() {
        return getSortedData();
    }
}
