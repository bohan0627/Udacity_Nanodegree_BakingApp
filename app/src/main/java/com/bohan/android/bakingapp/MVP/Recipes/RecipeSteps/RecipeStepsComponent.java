package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.MVP.Utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepoComponent.class, modules = RecipeStepsPresenterModule.class)
interface RecipeStepsComponent {

    void inject(RecipeStepsActivity recipeStepActivity);
}
