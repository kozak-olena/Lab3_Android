package com.example.lab3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;

public class Activity_Read extends AppCompatActivity {

    private DBHelper _dbHelper;
    private DatabaseHandler _databaseHandler;
    private Cursor _cursor;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        _dbHelper = new DBHelper(this);
        _databaseHandler = new DatabaseHandler(_dbHelper);
        _cursor = _databaseHandler.readData();

        if (_cursor.moveToFirst()) {
            int idIndex = _cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = _cursor.getColumnIndex(DBHelper.KEY_FULL_NAME);
            int timeIndex = _cursor.getColumnIndex(DBHelper.KEY_DATE);

            while (_cursor.moveToNext()) {
                String localTime = DateTimeHandler.utcToLocalTime(_cursor, timeIndex);
                String name = _cursor.getString(nameIndex);
                int id = _cursor.getInt(idIndex);

                TableRow tr = new TableRow(this);

                TextView textViewId = new TextView(this);
                textViewId.setText(String.valueOf(id));

                TextView textViewName = new TextView(this);
                textViewName.setText(name);

                TextView textViewDate = new TextView(this);
                textViewDate.setText(localTime);

                tr.addView(textViewId);
                tr.addView(textViewName);
                tr.addView(textViewDate);

                tableLayout.addView(tr);
            }
        } else {
            //TODO: add when table is empty
        }
        _cursor.close();
    }
}