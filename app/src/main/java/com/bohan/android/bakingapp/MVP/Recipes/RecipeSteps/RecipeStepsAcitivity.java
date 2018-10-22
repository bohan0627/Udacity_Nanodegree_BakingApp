package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeStepsAcitivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "RECIPE_ID";
    private static final int DEFAULT_RECIPE_ID = 1;
    public static final String EXTRA_STEP_ID = "STEP_ID";
    private static final int DEFAULT_STEP_ID = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static Intent parseIntent(Context context, int recipeId, int stepId) {
        Intent intent = new Intent(context, RecipeStepsAcitivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);
        intent.putExtra(EXTRA_STEP_ID, stepId);
        return intent;
    }
}
