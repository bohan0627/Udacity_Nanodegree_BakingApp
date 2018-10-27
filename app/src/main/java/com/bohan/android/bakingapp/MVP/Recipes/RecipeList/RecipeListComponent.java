package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.MVP.Utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepoComponent.class, modules = RecipeListPresenterModule.class)
interface RecipeListComponent {

    void inject(RecipeListActivity recipeListActivity);
}
