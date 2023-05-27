package com.example.calorytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    Context context;

    public DB(Context context, String db){
        super(context, db, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE Table Calories (id integer primary key autoincrement, caloryTotal integer," +
                "units integer, comma integer, foodID integer, datetimer datetime , foreign key (foodID) references Food(id))");

        db.execSQL("create table Food (id integer primary key autoincrement, caloryPerUnit integer," +
                " nameOfProduct text, unitTypeID integer, foreign key (unitTypeID) references Unit(id))");
    
        db.execSQL("create table Unit (id integer primary key autoincrement, type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Calories");
        db.execSQL("drop table if exists Food");
        db.execSQL("drop table if exists Unit");
    }


    public void addToCalories(Food f, float units, int day, int month, int year, int hour, int minute){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase dbWrite = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from Food" ,null);
        ContentValues cv = new ContentValues();

        int foodId;
        String dateTime = year + "-" + month + "-" + day + "-" + hour + "-" + minute;


        if(c.getCount()>0){
            while(c.moveToNext()){
                Food f1 = new Food(c.getInt(1), Integer.toString(c.getInt(2)) + "." + Integer.toString(c.getInt(3)), unitByID(c.getInt(4)));
                if(f.equals(f1)){
                    foodId = c.getInt(0); //int id of the food
                    cv.put("FoodID", foodId);
                }
            }
        }else {
            //food not found, insert into Food
            addFood(f);
            Cursor maxid = db.rawQuery("Select max(id) from Food",null);
            maxid.moveToFirst();
            foodId = maxid.getInt(0);
            cv.put("FoodID", foodId);
        }

        cv.put("units", (int) units); //up to 3 decimal places precision
        int comma = (int)(units*1000);
        comma = comma - (int)(units)*1000;
        cv.put("comma", comma);
        cv.put("datetimer", dateTime);

        if(f.getCalPerUnit() != -1) {
            int caloryTotal = (int) units * f.getCalPerUnit();
            cv.put("caloryTotal", caloryTotal);
        }
        
        long res = dbWrite.insert("Calories", null, cv);
        if (res == -1) Toast.makeText(context, "Inserting into Calories failed", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Successfully inserted into Calories", Toast.LENGTH_SHORT).show();
    }

    String unitByID(int a) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select type from Unit where id=?", new String[]{Integer.toString(a)});
        if(c.getCount()>0){
            c.moveToFirst();
            return c.getString(0);
        }else{
            return "-1";
        }

    }


    /**
     * Adds a food or a drink into the database
     * @param f the food or drink to be added
     */
    public void addFood(Food f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(f.getCalPerUnit() != -1) cv.put("caloryPerUnit", f.getCalPerUnit());
        cv.put("nameOfProduct", f.getNameOfProd());
        if (translateToUnitId(f.getUnitType()) != -1) cv.put("unitTypeID", translateToUnitId(f.getUnitType()));
        else{ 
            addUnit(f.getUnitType());
            cv.put("unitTypeID", translateToUnitId(f.getUnitType()));
        }
        
        long res = db.insert("Food", null, cv);
        if(res == -1) Toast.makeText(context, "Inserting into Food failed", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Successfully inserted into Food", Toast.LENGTH_SHORT).show();

    }

    private void addUnit(String unitType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("type", unitType);
        long res = db.insert("Unit", null, cv);
        if (res == -1) Toast.makeText(context, "Failed to insert into Unit", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Successfully inserted into Unit", Toast.LENGTH_SHORT).show();;

    }

    private int translateToUnitId(String unitType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Unit", null);

        if (c.getCount()>0){
            while(c.moveToNext()){
                if (c.getString(1).equals(unitType)) return c.getInt(0);
            }
        }
        return -1;
    }


    public ArrayList<String> getUnits(String s) {

        ArrayList<String> units = new ArrayList<>();
        units.add(s);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select type from Unit", null);

        if(c.getCount()>0){
            while(c.moveToNext()){
                units.add(c.getString(0));
            }
        }
        return units;

    }

    public ArrayList<Food> getFoods(String s) {

        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food(s,"Select"));
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from Food", null);

        if(c.getCount()>0){
            while(c.moveToNext()){
                foods.add(new Food(c.getInt(0), c.getString(1), unitByID(c.getInt(2))));
            }
        }
         return foods;
    }
}
