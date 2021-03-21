package com.example.lab3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;

public class Activity_Read extends AppCompatActivity implements View.OnClickListener {

    Button btn_read, btn_clear;

    private DBHelper _dbHelper;
    private DatabaseHandler _databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        btn_read = (Button) findViewById(R.id.readBtn);
        btn_read.setOnClickListener(this);

        btn_clear = (Button) findViewById(R.id.clearBtn);
        btn_clear.setOnClickListener(this);
        _dbHelper = new DBHelper(this);

        _databaseHandler = new DatabaseHandler(_dbHelper);
    }

    @Override
    public void onClick(View v) {
        try {

            _databaseHandler.readData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}