package com.gokha.mealseek;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Zhanat on 20.11.17.
 */

public class RecipeDetailsActivity extends AppCompatActivity {
    ArrayList<Ingredient> recipeIngrs;
    ArrayList<Integer> ingredientsIds;
    String ingr;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.recipe_details );

        databaseReference = FirebaseDatabase.getInstance().getReference( "recipes" );
        databaseReference1 = FirebaseDatabase.getInstance().getReference("ingredients");
        recipeIngrs = new ArrayList<Ingredient>(  );
        Recipe recipe = (Recipe)getIntent().getExtras().getSerializable("recipe");
        new RecipeDetailsActivity.GetDataFromFirebase().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR);
        ingredientsIds = recipe.getIngredients();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshoot : dataSnapshot.getChildren()) {
                    Log.e("SnapShot",dataSnapshoot.toString());
                    Ingredient data = dataSnapshoot.getValue(Ingredient.class);
                    if (ingredientsIds.contains(data.getId())) {
                        recipeIngrs.add( data );
                    }

                }

                for (Ingredient ingredient: recipeIngrs){
                    Log.e("Ingredient",ingredient.getName());
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed to read value." + databaseError.toException());
            }
        });

        for (Ingredient ingredient: recipeIngrs) {
            if (recipeIngrs.size() != 0) {
                ingr = ingredient.getName() + " " + ingredient.getAmount() + " " + ingredient.getUnits() + "\n";

            }
            else{
                ingr = "Ingridients haven't load";
            }
        }

        TextView mealName = (TextView) findViewById( R.id.mealNameTextView );
        TextView instruction = (TextView) findViewById( R.id.instructionTextView );
        TextView ingredients = (TextView) findViewById( R.id.ingredientsTextView );



        mealName.setText( recipe.getMealName() );
        instruction.setText( recipe.getInstruction() );
        ingredients.setText( ingr );

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