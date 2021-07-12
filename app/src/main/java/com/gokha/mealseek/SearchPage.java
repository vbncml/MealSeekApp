package com.gokha.mealseek;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.v4.view.MenuItemCompat.getActionView;

/**
 * Created by Zhanat on 12.11.17.
 */

public class SearchPage extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView listView;
    public ArrayList<Ingredient> ingredientArrayList ;
    public ArrayList<Ingredient> chIngredients;
    DatabaseReference databaseReference;
    private RecyclerView rv;
    Context mContext;
    //SearchView searchView;
    RecyclerViewAdapter adapter;
    Button btn;
    Toolbar toolbar;
    //IngredientList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        databaseReference = FirebaseDatabase.getInstance().getReference("ingredients");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.ingredientsRecyclerView);
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( this ) );

        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        chIngredients = new ArrayList<Ingredient>(  );
        ingredientArrayList = new ArrayList<Ingredient>();
        //searchView = (SearchView) findViewById(R.id.search);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshoot : dataSnapshot.getChildren()) {
                    Log.e("SnapShot",dataSnapshoot.toString());
                    Ingredient data = dataSnapshoot.getValue(Ingredient.class);
                    ingredientArrayList.add(data);
                }
                for (Ingredient ingredient: ingredientArrayList){
                    Log.e("Ingredient",ingredient.getName());
                }
                adapter = new RecyclerViewAdapter(ingredientArrayList, chIngredients);
                rv.setAdapter( adapter );
                rv.invalidate();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed to read value." + databaseError.toException());
            }
        });

        btn = (Button)findViewById(R.id.button3);

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPage.this, RecipesListActivity.class);
                intent.putExtra( "chosenIng", chIngredients );
                startActivity(intent);
            }
        } );




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
       // SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        s = s.toLowerCase();
        ArrayList<Ingredient> newList = new ArrayList<>();
        for(Ingredient ingredient : ingredientArrayList){
            String name = ingredient.getName().toLowerCase();
            if(name.contains(s))
                newList.add(ingredient);
        }

        adapter.setFilter(newList);
        return true;
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
