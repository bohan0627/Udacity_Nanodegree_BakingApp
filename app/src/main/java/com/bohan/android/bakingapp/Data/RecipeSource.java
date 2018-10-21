package com.bohan.android.bakingapp.Data;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.BaseModel.Step;

import java.util.List;
import io.reactivex.Observable;

public interface RecipeSource {
    Observable<List<Recipe>> getRecipes();

    Observable<List<Ingredient>> getIngredientsById(int recipeId);

    Observable<List<Ingredient>> getIngredientsByName(String recipeName);

    Observable<List<Step>> getSteps(int recipeId);

    void storeRecipes(List<Recipe> recipes);
}
