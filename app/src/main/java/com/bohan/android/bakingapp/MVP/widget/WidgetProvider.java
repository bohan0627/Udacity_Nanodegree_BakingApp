package com.bohan.android.bakingapp.MVP.widget;

/**
 * Created by Bo Han.
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.bohan.android.bakingapp.MVP.BakingApp;
import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.R;
import com.bohan.android.bakingapp.MVP.Utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class WidgetProvider extends AppWidgetProvider {

    @Inject
    WidgetHelper widgetHelper;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] widgetIds) {
        super.onUpdate(context, appWidgetManager, widgetIds);

        Timber.d("Updating");

        DaggerWidgetHelperComponent.builder()
                .recipeRepoComponent(
                        ((BakingApp) context.getApplicationContext()).getRecipeRepositoryComponent())
                .build()
                .inject(this);

        for (int widgetId : widgetIds) {
            String recipeName = widgetHelper.getRecipeNameFromPrefs(widgetId);

            widgetHelper
                    .getIngredientsList(recipeName)
                    .take(1)
                    .subscribe(
                            ingredients ->
                                    WidgetProvider
                                            .updateWidgetContent(context, appWidgetManager, widgetId, recipeName,
                                                    ingredients),
                            throwable ->
                                    Timber.d("Error: unable to load data."));
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        DaggerWidgetHelperComponent.builder()
                .recipeRepoComponent(
                        ((BakingApp) context.getApplicationContext()).getRecipeRepositoryComponent())
                .build()
                .inject(this);

        for (int appWidgetId : appWidgetIds) {
            widgetHelper.deleteRecipeFromPrefs(appWidgetId);
        }
    }

    public static void updateWidgetContent(Context context, AppWidgetManager appWidgetManager,
                                              int widgetId, String recipeName, List<Ingredient> ingredients) {

        Timber.d("updating Widget Content...");
        Timber.d("id: " + widgetId + ", name: " + recipeName + "ingredients: " + ingredients.size());

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredients_list);
        views.setTextViewText(R.id.widget_recipe_name, recipeName);
        views.removeAllViews(R.id.widget_ingredients_container);

        for (Ingredient ingredient : ingredients) {
            RemoteViews ingredientView = new RemoteViews(context.getPackageName(),
                    R.layout.widget_ingredients_list_item);

            String line = StringUtils.formatIngdedient(
                    context, ingredient.ingredient(), ingredient.quantity(), ingredient.measure());

            ingredientView.setTextViewText(R.id.widget_ingredient_name, line);
            views.addView(R.id.widget_ingredients_container, ingredientView);
        }

        appWidgetManager.updateAppWidget(widgetId, views);
    }

}

