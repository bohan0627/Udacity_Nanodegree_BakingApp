package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.MVP.BasePresenter;
import com.bohan.android.bakingapp.MVP.BaseView;
import com.bohan.android.bakingapp.MVP.Utils.RecipesIdlingResource;

import java.util.List;

public interface RecipeListContract {

    interface View extends BaseView<Presenter> {

        void showRecipeList(List<Recipe> recipeList);

        void showLoadingIndicator(boolean show);

        void showCompletedMessage();

        void showErrorMessage();

        void showRecipeDetails(int recipeId);
    }

    interface Presenter extends BasePresenter {

        void loadRecipesFromRepo(boolean forcedSync, RecipesIdlingResource resource);

        void openRecipeDetails(int recipeId);
    }
}
