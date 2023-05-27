package com.example.calorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewActivity extends AppCompatActivity {

    private EditText editNameOfFood, editKCalPerUnit, editUnit, editNumOfUnits;
    private Spinner spUnit, spExistingFood, spDay, spMonth, spYear, spHour, spMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);




    }
}