package com.bohan.android.bakingapp.MVP.widget;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.Data.RecipeRepo;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WidgetHelper {

    private final RecipeRepo recipeRepo;

    @Inject
    public WidgetHelper(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    Set<String> getRecipeNamesFromPrefs() {
        return recipeRepo.getPreferencesHelper().getRecipeNamesList();
    }

    void deleteRecipeFromPrefs(int widgetId) {
        recipeRepo.getPreferencesHelper().deleteRecipeName(widgetId);
    }

    void saveRecipeNameToPrefs(int widgetId, String name) {
        recipeRepo.getPreferencesHelper().saveChosenRecipeName(widgetId, name);
    }

    String getRecipeNameFromPrefs(int widgetId) {
        return recipeRepo.getPreferencesHelper().getChosenRecipeName(widgetId);
    }

    Observable<List<Ingredient>> getIngredientsList(String recipeName) {
        return recipeRepo.getIngredientsByName(recipeName);
    }
}
