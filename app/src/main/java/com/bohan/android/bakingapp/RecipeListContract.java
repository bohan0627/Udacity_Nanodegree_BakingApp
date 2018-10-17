package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.BaseModel.Recipe;

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
