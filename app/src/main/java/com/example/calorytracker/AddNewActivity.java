package com.example.calorytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

public class AddNewActivity extends AppCompatActivity {

    private EditText editNameOfFood, editKCalPerUnit, editUnit, editNumOfUnits;
    private Spinner spUnit, spExistingFood, spDay, spMonth, spYear, spHour, spMin;
    private Button btnAddKcals, btnNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        editNameOfFood = (EditText) findViewById(R.id.txtFoodType);
        editKCalPerUnit = (EditText) findViewById(R.id.editKcalPerUnit);
        editUnit = (EditText) findViewById(R.id.editUnit);
        editNumOfUnits = (EditText) findViewById(R.id.editNumOfUnits);
        spUnit = (Spinner) findViewById(R.id.spUnit);
        spExistingFood = (Spinner) findViewById(R.id.spExistingFood);
        spDay = (Spinner) findViewById(R.id.spDay);
        spMonth = (Spinner) findViewById(R.id.spMonth);
        spYear = (Spinner) findViewById(R.id.spYear);
        spHour = (Spinner) findViewById(R.id.spHour);
        spMin = (Spinner) findViewById(R.id.spMin);
        btnNow = (Button) findViewById(R.id.btnNow);
        btnAddKcals = (Button) findViewById(R.id.btnAddKCals);

        ArrayList<Spinner> spinners = new ArrayList<>();
        spinners.add(spUnit);
        spinners.add(spExistingFood);
        spinners.add(spDay);
        spinners.add(spMonth);
        spinners.add(spYear);
        spinners.add(spHour);
        spinners.add(spMin);

        ArrayList<ArrayList> arr = new ArrayList<>();

        ArrayList<String> units = new ArrayList<>();
        units.add(0,"Select...");

        ArrayList<String> foodNames = new ArrayList<>();
        foodNames.add(0,"Select Food Name");

        ArrayList<Integer> days = new ArrayList<>();
        days.add(0,31);

        ArrayList<Integer> months = new ArrayList<>();
        months.add(0, 12);

        ArrayList<Integer> years = new ArrayList<>();
        years.add(0, 2023);

        ArrayList<Integer> hours = new ArrayList<>();
        hours.add(0,23);

        ArrayList<Integer> mins = new ArrayList<>();
        mins.add(0,59);

        DB db = new DB(this, "KCals.db");

        units = db.getUnits(units.get(0));

        ArrayList<Food> foods = db.getFoods(foodNames.get(0));

        for (Food f: foods){
            foodNames.add(f.getNameOfProd());
        }


        for (int i = 2; i < 60; i++){
            if(i > 29){
                days.add(60-i);
            }
            if(i > 60-12){          //im lazy leave me alone xD
                months.add(60-i);
            }
            if(i > 60-23) {
                hours.add(60 - i);
            }
            mins.add(60-i);
        }

        for (int i = Year.now().getValue()-1; i > 1999; i--) years.add(i);

        arr.add(units);
        arr.add(foodNames);
        arr.add(days);
        arr.add(months);
        arr.add(years);
        arr.add(hours);
        arr.add(mins);

        ArrayList<ArrayAdapter> adapters = setupSps(spinners, arr);

        btnNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d = MonthDay.now().getDayOfMonth();
                int mo = MonthDay.now().getMonthValue();
                int y = Year.now().getValue();
                Calendar calendar = Calendar.getInstance();
                int h = calendar.get(Calendar.HOUR_OF_DAY);
                int mi = calendar.get(Calendar.MINUTE);

                spDay.setSelection(31-d);
                spMonth.setSelection(12-mo);
                spYear.setSelection(2023-y);
                spHour.setSelection(23-h);
                spMin.setSelection(59-mi);
            }
        });


        btnAddKcals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db1 = new DB(AddNewActivity.this, "KCals.db");

                if(editNameOfFood.getText().toString().equals("") && spExistingFood.getSelectedItemPosition() == 0){
                    Toast.makeText(AddNewActivity.this, "Please fill in the name of the food, or select a food", Toast.LENGTH_SHORT).show();
                } else if(editNumOfUnits.getText().toString().equals("")){
                    Toast.makeText(AddNewActivity.this, "Please fill in, how many Units that was", Toast.LENGTH_SHORT).show();
                }else if(spUnit.getSelectedItemPosition() == 0 && editUnit.getText().toString().equals("")){
                    Toast.makeText(AddNewActivity.this, "Please give a Unit to measure this in", Toast.LENGTH_SHORT).show();
                }else {

                    if (spExistingFood.getSelectedItemPosition() != 0) {
                        Food f = foods.get(spExistingFood.getSelectedItemPosition() - 1);
                    } else {
                        if (editKCalPerUnit.getText().toString().equals("") && spUnit.getSelectedItemPosition() == 0) {
                            Food f = new Food(editNameOfFood.getText().toString(), editUnit.getText().toString());
                        } else if (editKCalPerUnit.getText().toString().equals("") && spUnit.getSelectedItemPosition() != 0) {
                            Food f = new Food(editNameOfFood.getText().toString(), db1.unitByID(spUnit.getSelectedItemPosition() - 1));
                        } else if (spUnit.getSelectedItemPosition() != 0) {
                            Food f = new Food(Integer.parseInt(editKCalPerUnit.getText().toString()), editNameOfFood.getText().toString(), db1.unitByID(spUnit.getSelectedItemPosition() - 1));
                        } else {
                            Food f = new Food(Integer.parseInt(editKCalPerUnit.getText().toString()), editNameOfFood.getText().toString(), editUnit.getText().toString());
                        }
                    }
                    finish();
                }
            }
        });







    }

    private ArrayList<ArrayAdapter> setupSps(ArrayList<Spinner> spinners, ArrayList<ArrayList> arr) {

        ArrayList<ArrayAdapter> adapters = new ArrayList<>();


        for (int i = 0; i < spinners.size(); i++) {
            Spinner sp = spinners.get(i);
            ArrayList ar = arr.get(i);

            adapters.add(new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar));
            adapters.get(i).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapters.get(i));
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return adapters;
    }
}