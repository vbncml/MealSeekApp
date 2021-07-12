package com.gokha.mealseek;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Zhanat on 19.11.17.
 */

public class RecipesListActivity extends Activity {

    private ListView listView;
    ArrayList<Recipe> recipes;
    ArrayList<Recipe> sortedRecipes;
    ArrayList<Ingredient> chIngredients;
    DatabaseReference databaseReference;
    RecipesList adapter;
    RecyclerView rv;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        chIngredients = (ArrayList<Ingredient>) getIntent().getExtras().getSerializable("chosenIng");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        sortedRecipes = new ArrayList<Recipe>(  );

        recipes = new ArrayList<Recipe>(  );

        mContext = RecipesListActivity.this;

        rv = (RecyclerView) findViewById(R.id.recipes_listView);
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( this ) );

        new RecipesListActivity.GetDataFromFirebase().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR);

        recipes = new ArrayList<Recipe>();

        for (Ingredient chIngr: chIngredients){
            Log.e("Ingr",chIngr.getName());
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshoot : dataSnapshot.getChildren()) {

                    Recipe data = dataSnapshoot.getValue(Recipe.class);
                    recipes.add(data);

                    for (Ingredient ingr : chIngredients){

                        if (data.getIngredients().contains( ingr.getId() )){
                            sortedRecipes.add(data);
                        }
                    }

                    for (Recipe recipe: recipes){
                        Log.e("Recipe", recipe.getMealName());
                    }
                }
                adapter = new RecipesList(recipes, mContext);
                rv.setAdapter( adapter );
                rv.invalidate();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed to read value." + databaseError.toException());
            }
        });


    }

    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute( aBoolean );
        }
    }
}
