package com.example.lab3_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Add extends AppCompatActivity implements View.OnClickListener {

    Button btn_add;
    EditText add_id, add_name;

    private DBHelper _dbHelper;
    private DatabaseHandler _databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add);

        btn_add = (Button) findViewById(R.id.btnAdd);
        btn_add.setOnClickListener(this);

        add_name = (EditText) findViewById(R.id.classmates_name);

        _dbHelper = new DBHelper(this);
        _databaseHandler = new DatabaseHandler(_dbHelper);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        _databaseHandler.insertData(add_name);

    }
}