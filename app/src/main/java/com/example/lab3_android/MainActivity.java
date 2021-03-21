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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    public void openContactsTable(View view) {
        Intent intent = new Intent(this, Activity_Read.class);
        startActivity(intent);
    }

    public void openAddActivity(View view) {
        Intent intent = new Intent(this, Activity_Add.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        DatabaseHandler databaseHandler = new DatabaseHandler(dbHelper);
        databaseHandler.updateLastRecord();

    }
}