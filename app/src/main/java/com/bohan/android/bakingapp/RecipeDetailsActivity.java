package com.bohan.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final int DEFAULT_RECIPE_ID = 1;
    public static final String EXTRA_RECIPE_ID = "RECIPE_ID";

    @Inject
    RecipeDetailsPresenter
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setActionBar(){
        ActionBar bar = getSupportActionBar();

        if(bar != null)
            bar.setDisplayHomeAsUpEnabled(true);
    }
}
