package com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.bohan.android.bakingapp.BakingApp;
import com.bohan.android.bakingapp.R;
import com.bohan.android.bakingapp.Utils.FragmentUtils;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final int DEFAULT_RECIPE_ID = 1;
    public static final String EXTRA_RECIPE_ID = "RECIPE_ID";

    @Inject
    RecipeDetailsPresenter recipeDetailsPresenter;

    public static Intent prepareIntent(Context context, int recipeId) {
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);

        setUpActionBar();

        int recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, DEFAULT_RECIPE_ID);

        RecipeDetailsFragment recipeDetailsFragment =
                (RecipeDetailsFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.detailsFragmentContainer);

        if (recipeDetailsFragment == null) {
            recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId);
            FragmentUtils.addFragment(getSupportFragmentManager(), recipeDetailsFragment,
                    R.id.detailsFragmentContainer);
        }

        DaggerRecipeDetailsComponent.builder()
                .recipeRepositoryComponent(((BakingApp) getApplication()).getRecipeRepositoryComponent())
                .recipeDetailsPresenterModule(new RecipeDetailsPresenterModule(recipeDetailsFragment, recipeId))
                .build()
                .inject(this);
    }

    private void setActionBar() {
        ActionBar bar = getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
