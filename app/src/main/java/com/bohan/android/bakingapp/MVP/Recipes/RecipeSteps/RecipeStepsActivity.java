package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

/**
 * Created by Bo Han.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.bohan.android.bakingapp.MVP.BakingApp;
import com.bohan.android.bakingapp.R;
import com.bohan.android.bakingapp.MVP.Utils.FragmentUtils;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.annotations.Nullable;

public class RecipeStepsActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE_ID = "RECIPE_ID";
    private static final int DEFAULT_RECIPE_ID = 1;

    public static final String EXTRA_STEP_ID = "STEP_ID";
    private static final int DEFAULT_STEP_ID = 0;

    @Inject
    RecipeStepsPresenter recipeStepPresenter;

    public static Intent prepareIntent(Context context, int recipeId, int stepId) {
        Intent intent = new Intent(context, RecipeStepsActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);
        intent.putExtra(EXTRA_STEP_ID, stepId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_steps);

        int recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, DEFAULT_RECIPE_ID);
        int stepId = getIntent().getIntExtra(EXTRA_STEP_ID, DEFAULT_STEP_ID);

        RecipeStepsFragment recipeStepFragment =
                (RecipeStepsFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.stepFragmentContainer);

        if (recipeStepFragment == null) {
            recipeStepFragment = RecipeStepsFragment.newInstance(stepId);
            FragmentUtils.addFragment(getSupportFragmentManager(), recipeStepFragment,
                    R.id.stepFragmentContainer);
        }

        DaggerRecipeStepsComponent.builder()
                .recipeRepoComponent(((BakingApp) getApplication()).getRecipeRepositoryComponent())
                .recipeStepsPresenterModule(new RecipeStepsPresenterModule(recipeStepFragment, recipeId))
                .build()
                .inject(this);
    }
}
