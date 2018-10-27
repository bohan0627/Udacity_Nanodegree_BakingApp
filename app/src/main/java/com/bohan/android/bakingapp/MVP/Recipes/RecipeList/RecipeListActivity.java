package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

/**
 * Created by Bo Han.
 */

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.IdlingResource;
import android.view.Menu;
import android.view.MenuItem;
import com.bohan.android.bakingapp.MVP.BakingApp;
import com.bohan.android.bakingapp.R;
import com.bohan.android.bakingapp.MVP.Utils.RecipesIdlingResource;
import com.bohan.android.bakingapp.MVP.Utils.FragmentUtils;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeListActivity extends AppCompatActivity{

    @Inject
    RecipeListPresenter recipeListPresenter;

    @Nullable
    private RecipesIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        RecipeListFragment recipeListFragment =
                (RecipeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (recipeListFragment == null) {
            recipeListFragment = RecipeListFragment.newInstance();
            FragmentUtils.addFragment(getSupportFragmentManager(), recipeListFragment,
                    R.id.fragmentContainer);
        }

        DaggerRecipeListComponent.builder()
                .recipeRepoComponent(((BakingApp) getApplication()).getRecipeRepositoryComponent())
                .recipeListPresenterModule(new RecipeListPresenterModule(recipeListFragment))
                .build()
                .inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_refresh) {
            recipeListPresenter.loadRecipesFromRepo(true, idlingResource);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RecipesIdlingResource();
        }
        return idlingResource;
    }

}
