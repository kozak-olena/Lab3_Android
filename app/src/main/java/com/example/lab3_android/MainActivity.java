package com.example.lab3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void openContactsTable(View view){
        Intent intent = new Intent(this, Activity_Read.class);
        startActivity(intent);
    }

    public void openAddActivity(View view){
        Intent intent = new Intent(this, Activity_Add.class);
        startActivity(intent);
    }
}