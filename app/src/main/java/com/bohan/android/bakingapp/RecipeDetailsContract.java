package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Step;

import java.util.List;

public interface RecipeDetailsContract {

    interface DetailsView extends BaseView<Presenter> {

        void ingredientsList(List<Ingredient> ingredients);

        void stepsList(List<Step> steps);

        void errorMessage();

        void recipeName(String recipeName);

        void stepDetails(int stepId);

        void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl);
    }

    interface Presenter extends BasePresenter {

        void loadRecipeNameFromRepo();

        void loadIngredientsFromRepo();

        void loadStepsFromRepo();

        void openStepDetails(int stepId);

        void fetchStepData(int stepId);
    }
}