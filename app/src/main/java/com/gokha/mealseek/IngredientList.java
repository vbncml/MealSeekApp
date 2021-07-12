package com.gokha.mealseek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zhanat on 12.11.17.
 */

public class IngredientList extends RecyclerView.Adapter<IngredientList.MyViewHolder> {

    private Context mContext;
    private List<Ingredient> mIngredientList;
    public List<Ingredient> chIngredients;

    public IngredientList(Context context, List<Ingredient> ingredientList)
    {
        mContext = context;
        mIngredientList = ingredientList;

    }

    @Override
    public IngredientList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.list_item, parent, false );
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ingredient ingredient = mIngredientList.get(position);
        holder.setPosition(position);
        holder.mIngredientName.setText( ingredient.getName() );
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mIngredientName;

        int position;

        public MyViewHolder(View v){
            super(v);
            mIngredientName = (TextView) v.findViewById( R.id.list_item);
            v.setOnClickListener( this );
        }

        public void setPosition(int position){
            this.position = position;
        }

        @Override
        public void onClick(View view) {

        }


    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }



}

