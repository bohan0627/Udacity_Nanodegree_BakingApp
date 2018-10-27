package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

/**
 * Created by Bo Han.
 */


import dagger.Module;
import dagger.Provides;

@Module
public class RecipeStepsPresenterModule {
    private final RecipeStepsContract.View view;
    private final int recipeId;

    RecipeStepsPresenterModule(RecipeStepsContract.View view, int recipeId) {
        this.view = view;
        this.recipeId = recipeId;
    }

    @Provides
    RecipeStepsContract.View provideRecipeStepContractView() {
        return view;
    }

    @Provides
    int provideRecipeId() {
        return recipeId;
    }
}
