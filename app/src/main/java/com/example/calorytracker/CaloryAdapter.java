package com.example.calorytracker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CaloryAdapter extends RecyclerView.Adapter<CaloryAdapter.ViewHolder> {

    private ArrayList<Food> foods;
    private Context context;
    private ViewHolder holder;

    public CaloryAdapter(Context context){this.context = context;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_calory_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DB db = new DB(context, "KCals.db");
        holder.txtNameOfProd.setText(holder.txtNameOfProd.getText().toString() + foods.get(position).getNameOfProd());
        holder.txtUnit.setText(holder.txtUnit.getText().toString() + foods.get(position).getUnitType());
        holder.txtCalPerUnit.setText(holder.txtCalPerUnit.getText().toString() + String.valueOf(foods.get(position).getCalPerUnit()));
        ArrayList<CalEntry> cals = db.getEverything();

        holder.txtID.setText(String.valueOf(cals.get(position).getId()));
        holder.txtNumOfUnits.setText(holder.txtNumOfUnits.getText().toString() + String.valueOf(cals.get(position).getUnitNums()) + "." + String.valueOf(cals.get(position).getUnitComma()));
        holder.txtDateAndTime.setText(holder.txtDateAndTime.getText().toString() + cals.get(position).getDateTime());
        holder.txtCalTotal.setText(holder.txtCalTotal.getText().toString() + cals.get(position).getCalTotal());



    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void setFoods(ArrayList<Food> fo){
        this.foods = fo;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNameOfProd, txtCalPerUnit, txtNumOfUnits, txtUnit, txtCalTotal, txtDateAndTime, txtID;
        public CardView parent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtID = itemView.findViewById(R.id.txtCalID);
            txtNameOfProd = (TextView) itemView.findViewById(R.id.txtNameOfProd);
            txtCalPerUnit = (TextView) itemView.findViewById(R.id.txtCalPerUnit);
            txtNumOfUnits = (TextView) itemView.findViewById(R.id.txtUnitNums);
            txtUnit = (TextView) itemView.findViewById(R.id.txtUnit);
            txtCalTotal = (TextView) itemView.findViewById(R.id.txtCalTotal);
            txtDateAndTime = (TextView) itemView.findViewById(R.id.txtDateTimer);
            parent = itemView.findViewById(R.id.cvCalory);
        }
    }

}
