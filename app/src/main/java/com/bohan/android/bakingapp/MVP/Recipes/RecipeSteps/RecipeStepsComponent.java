package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepoComponent.class, modules = RecipeStepsPresenterModule.class)
interface RecipeStepsComponent {

    void inject(RecipeStepsActivity recipeStepActivity);
}
