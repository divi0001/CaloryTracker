package com.example.calorytracker;

public class CalEntry {
    //String[] fromFieldNames = new String[]{"id", "caloryTotal", "units", "comma", "datetimer", "caloryPerUnit", "nameOfProduct", "type"};
    private int id, calTotal, unitNums, unitComma, calPerUnit;
    private String dateTime, nameOfProduct, unitType;

    public CalEntry(int id, int calTotal, int unitNums, int unitComma, int calPerUnit, String dateTime, String nameOfProduct, String unitType) {
        this.id = id;
        this.calTotal = calTotal;
        this.unitNums = unitNums;
        this.unitComma = unitComma;
        this.calPerUnit = calPerUnit;
        this.dateTime = dateTime;
        this.nameOfProduct = nameOfProduct;
        this.unitType = unitType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalTotal() {
        return calTotal;
    }

    public void setCalTotal(int calTotal) {
        this.calTotal = calTotal;
    }

    public int getUnitNums() {
        return unitNums;
    }

    public void setUnitNums(int unitNums) {
        this.unitNums = unitNums;
    }

    public int getUnitComma() {
        return unitComma;
    }

    public void setUnitComma(int unitComma) {
        this.unitComma = unitComma;
    }

    public int getCalPerUnit() {
        return calPerUnit;
    }

    public void setCalPerUnit(int calPerUnit) {
        this.calPerUnit = calPerUnit;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "CalEntry{" +
                "id=" + id +
                ", calTotal=" + calTotal +
                ", unitNums=" + unitNums +
                ", unitComma=" + unitComma +
                ", calPerUnit=" + calPerUnit +
                ", dateTime='" + dateTime + '\'' +
                ", nameOfProduct='" + nameOfProduct + '\'' +
                ", unitType='" + unitType + '\'' +
                '}';
    }
}
