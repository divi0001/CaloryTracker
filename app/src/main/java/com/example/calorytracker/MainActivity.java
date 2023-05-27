package com.example.calorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btnOverview, btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOverview = (Button) findViewById(R.id.btnOverview);
        btnAddNew = (Button) findViewById(R.id.btnAddNew);

        btnOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iOverview = new Intent(MainActivity.this, OverViewActivity.class);
                startActivity(iOverview);
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAdd = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(iAdd);
            }
        });



    }
}