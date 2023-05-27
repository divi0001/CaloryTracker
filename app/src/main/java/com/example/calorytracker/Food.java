package com.example.calorytracker;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Food {

    private int calPerUnit = -1;
    private String nameOfProd, unitType;



    public Food(int calPerUnit, String nameOfProd, String unitType) {
        this.calPerUnit = calPerUnit;
        this.nameOfProd = nameOfProd;
        this.unitType = unitType;
    }

    public Food(String nameOfProd, String unitType){
        this.nameOfProd = nameOfProd;
        this.unitType = unitType;
    }


    public int getCalPerUnit() {
        return calPerUnit;
    }

    public void setCalPerUnit(int calPerUnit) {
        this.calPerUnit = calPerUnit;
    }

    public String getNameOfProd() {
        return nameOfProd;
    }

    public void setNameOfProd(String nameOfProd) {
        this.nameOfProd = nameOfProd;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return getCalPerUnit() == food.getCalPerUnit() && Objects.equals(getNameOfProd(), food.getNameOfProd()) && Objects.equals(getUnitType(), food.getUnitType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCalPerUnit(), getNameOfProd(), getUnitType());
    }

    @Override
    public String toString() {
        return "Food{" +
                "calPerUnit=" + calPerUnit +
                ", nameOfProd='" + nameOfProd + '\'' +
                ", unitType='" + unitType + '\'' +
                '}';
    }
}
