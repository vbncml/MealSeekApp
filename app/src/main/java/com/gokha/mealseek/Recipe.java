package com.gokha.mealseek;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Zhanat on 19.11.17.
 */

public class Recipe implements Serializable {


    ArrayList<Integer> ingredients;
    String instruction;
    String mealName;
    String imageUrl;
    Integer id;

    public Recipe(){}

    public Recipe(Integer Id, ArrayList<Integer> ingredients, String instruction, String mealName, String imageUrl)
    {
        this.id = id;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.mealName = mealName;
        this.imageUrl = imageUrl;
    }
    public String getMealName(){
        return mealName;
    }
    public String getInstruction(){
        return instruction;
    }
    public ArrayList<Integer> getIngredients(){
        return ingredients;
    }
    public Integer getId() { return id; }
    public String getimageUrl() { return imageUrl; }

    @Override
    public String toString() {
        return mealName;
    }

}
