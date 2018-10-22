package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.VisibleForTesting;
//import android.support.v7.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.IdlingResource;
import android.view.Menu;
import android.view.MenuItem;

import com.bohan.android.bakingapp.R;
import com.bohan.android.bakingapp.RecipesIdlingResource;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeListActivity extends AppCompatActivity {

    @Inject
    RecipeListPresenter recipeListPresenter;

    @Nullable
    private RecipesIdlingResource idlingResource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int recipeId = item.getItemId();
        if(recipeId == R.id.action_refresh){

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is only visible to unit-test
     * @return IdlingResource
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RecipesIdlingResource();
        }
        return idlingResource;
    }
}
