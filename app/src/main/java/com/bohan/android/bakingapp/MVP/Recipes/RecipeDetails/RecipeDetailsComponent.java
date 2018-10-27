package com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepoComponent.class, modules = RecipeDetailsPresenterModule.class)
public interface RecipeDetailsComponent {

    void inject(RecipeDetailsActivity recipeDetailsActivity);
}
