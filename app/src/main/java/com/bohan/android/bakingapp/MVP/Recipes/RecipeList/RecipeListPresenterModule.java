package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

/**
 * Created by Bo Han.
 */

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
