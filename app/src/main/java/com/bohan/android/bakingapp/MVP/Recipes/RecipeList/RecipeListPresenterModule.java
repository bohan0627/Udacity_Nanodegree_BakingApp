package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

//import com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails.RecipeDetailsContract;

import dagger.Module;
import dagger.Provides;
@Module
public class RecipeListPresenterModule {
    private final RecipeListContract.View view;

    RecipeListPresenterModule(RecipeListContract.View view) {
        this.view = view;
    }

    @Provides
    RecipeListContract.View provideRecipeListContractView() {
        return view;
    }
}
