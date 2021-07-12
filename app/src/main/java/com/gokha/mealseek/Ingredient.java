package com.gokha.mealseek;

import java.io.Serializable;

/**
 * Created by Zhanat on 12.11.17.
 */

public class Ingredient implements Serializable{

    int id;
    String name;
    String amount;
    String units;

    public Ingredient(){}

    public Ingredient(int id, String name,String amount,String units)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.units = units;
    }
    public int getId() { return id; }
    public String getName(){
        return name;
    }
    public String  getAmount(){
        return amount;
    }
    public String getUnits(){
        return units;
    }

    @Override
    public String toString() {
        return name;
    }
}

