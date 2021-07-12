package com.gokha.mealseek;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zhanat on 21.11.17.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;
    public ArrayList<Ingredient> chosenIngredients;

    RecyclerViewAdapter(ArrayList<Ingredient> ingredients, ArrayList<Ingredient> chIngredients ) {
        this.ingredients = ingredients;
        this.chosenIngredients = chIngredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(ingredients.get(position).getName());
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        int position;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.ingredient_name);
            name.setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            name.setBackgroundColor(Color.BLUE);
            Log.e("Chosen ingredient name:", ingredients.get(position).getName());
            chosenIngredients.add(ingredients.get(position));
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    public void setFilter(ArrayList<Ingredient> newList){

        ingredients = new ArrayList<>();
        ingredients.addAll(newList);
        notifyDataSetChanged();
    }
}
