package com.example.lab3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnChange;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = (Button) findViewById(R.id.btn3);
        btnChange.setOnClickListener(this);
        dbHelper = new DBHelper(this);


    }

    public void openContactsTable(View view){
        Intent intent = new Intent(this, Activity_Read.class);
        startActivity(intent);
    }

    public void openAddActivity(View view){
        Intent intent = new Intent(this, Activity_Add.class);
        startActivity(intent);
    }

   /* public void changeLastRecord(View view) throws ParseException {

       DatabaseHandler.sqLiteDatabase = DatabaseHandler.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_FULL_NAME, "Іванов Іван Іванович");
        String orderBy = DBHelper.KEY_DATE+" ASC ";
        Cursor cursor = DatabaseHandler.sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null, null,
                null, null, orderBy);
        cursor.moveToLast();


        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FULL_NAME);
        int timeIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);

        int updCount = DatabaseHandler.sqLiteDatabase.update(DBHelper.TABLE_CLASSMATES, contentValues,
                DBHelper.KEY_DATE + "= ?", new String[] {cursor.getString(timeIndex)});

        String str = cursor.getString(timeIndex);       //????? чого тут стрінг?
        Date date = DateTimeHandler.utcToLocalTime(Long.parseLong(str));
        String localTime = DateTimeHandler.getDateFormat(date);

        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                ", name = " + cursor.getString(nameIndex) +
                ", time = " + localTime);
    }*/

    @Override
    public void onClick(View v) {

       SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_FULL_NAME, "Іванов Іван Іванович");
        String orderBy = DBHelper.KEY_DATE+" ASC ";
        Cursor cursor =  sqLiteDatabase.query(DBHelper.TABLE_CLASSMATES, null, null, null,
                null, null, orderBy);
        cursor.moveToLast();


        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FULL_NAME);
        int timeIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);

        int updCount =  sqLiteDatabase.update(DBHelper.TABLE_CLASSMATES, contentValues,
                DBHelper.KEY_DATE + "= ?", new String[] {cursor.getString(timeIndex)});

        String str = cursor.getString(timeIndex);       //????? чого тут стрінг?
        Date date = null;
        try {
            date = DateTimeHandler.utcToLocalTime(Long.parseLong(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String localTime = DateTimeHandler.getDateFormat(date);

        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                ", name = " + cursor.getString(nameIndex) +
                ", time = " + localTime);
    }
}