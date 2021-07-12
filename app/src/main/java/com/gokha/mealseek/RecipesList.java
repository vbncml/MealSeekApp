package com.gokha.mealseek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zhanat on 19.11.17.
 */

class RecipesList extends RecyclerView.Adapter<RecipesList.ViewHolder> {

    private Context context;
    private List<Recipe> recipesList;
    private List<Ingredient> chIngredients;

    RecipesList(List<Recipe> recipesList, Context context)
    {
        //super( context, R.layout.recipes_list_item, recipesList);
        this.context = context;
        this.recipesList = recipesList;

    }

    @Override
    public RecipesList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.recipes_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mealName.setText( recipesList.get( position ).getMealName() );
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mealName;
        int position;
        ViewHolder(View itemView) {
            super(itemView);
            mealName = (TextView) itemView.findViewById(R.id.recipe_name);
            mealName.setOnClickListener( this );
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, RecipeDetailsActivity.class);
            Log.e("Recipe", recipesList.get(position).getMealName());
            intent.putExtra( "recipe", recipesList.get( position ));
            context.startActivity(intent);

        }
    }


}
