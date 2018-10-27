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

public class WidgetDataHelper {

    private final RecipeRepo recipeRepository;

    @Inject
    public WidgetDataHelper(RecipeRepo recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    Set<String> getRecipeNamesFromPrefs() {
        return recipeRepository.getPreferencesHelper().getRecipeNamesList();
    }

    void deleteRecipeFromPrefs(int widgetId) {
        recipeRepository.getPreferencesHelper().deleteRecipeName(widgetId);
    }

    void saveRecipeNameToPrefs(int appWidgetId, String name) {
        recipeRepository.getPreferencesHelper().saveChosenRecipeName(appWidgetId, name);
    }

    String getRecipeNameFromPrefs(int appWidgetId) {
        return recipeRepository.getPreferencesHelper().getChosenRecipeName(appWidgetId);
    }

    Observable<List<Ingredient>> getIngredientsList(String recipeName) {
        return recipeRepository.getIngredientsByName(recipeName);
    }
}
