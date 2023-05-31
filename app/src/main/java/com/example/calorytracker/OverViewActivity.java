package com.example.calorytracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OverViewActivity extends AppCompatActivity {


    RecyclerView recCalory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);

        recCalory = (RecyclerView) findViewById(R.id.recCal);

        DB db = new DB(this, "KCals.db");
        CaloryAdapter adapter = new CaloryAdapter(this);

        ArrayList<Food> foods = db.getFoods(null);
        adapter.setFoods(foods);
        recCalory.setAdapter(adapter);
        recCalory.setLayoutManager(new LinearLayoutManager(this));









    }
}