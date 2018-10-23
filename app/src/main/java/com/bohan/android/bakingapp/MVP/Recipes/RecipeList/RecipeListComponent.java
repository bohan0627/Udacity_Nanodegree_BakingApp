package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;


import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RecipeRepoComponent.class, modules = RecipeListPresenterModule.class)
interface RecipeListComponent {

    void inject(RecipeListActivity recipeListActivity);
}
