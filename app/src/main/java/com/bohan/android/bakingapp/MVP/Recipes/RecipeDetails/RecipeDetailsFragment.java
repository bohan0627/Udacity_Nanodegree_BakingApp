package com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Step;

import java.util.List;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsContract.DetailsView{

    private int recipeId;

    public RecipeDetailsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getArguments().getInt(RecipeDetailsActivity.EXTRA_RECIPE_ID);
    }

    @Override
    public void ingredientsList(List<Ingredient> ingredients) {

    }

    @Override
    public void stepsList(List<Step> steps) {

    }

    @Override
    public void errorMessage() {

    }

    @Override
    public void recipeName(String recipeName) {

    }

    @Override
    public void stepDetails(int stepId) {

    }

    @Override
    public void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl) {

    }

    @Override
    public void setBaseView(RecipeDetailsContract.Presenter baseView) {

    }
}
