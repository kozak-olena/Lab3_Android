package com.example.lab3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnChange, btnAdd, btnRead;
    DBHelper dbHelper;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnChange = (Button) findViewById(R.id.btn3);
        btnChange.setOnClickListener(this);

        btnAdd = (Button) findViewById(R.id.btn1);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btn2);
        btnRead.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        databaseHandler = new DatabaseHandler(dbHelper);

        DatabaseHandler.sqLiteDatabase.delete(DBHelper.TABLE_CLASSMATES, null, null);
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

        switch (v.getId()) {
            case R.id.btn1:
                openContactsTable(v);
                break;

            case R.id.btn2:
                openAddActivity(v);

                break;

            case R.id.btn3:
                databaseHandler.updateLastRecord();
                break;
        }

    }
}