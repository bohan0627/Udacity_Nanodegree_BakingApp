package com.bohan.android.bakingapp.Utils;

import android.content.ContentValues;
import android.database.Cursor;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.Data.LocalSource.IngredientContract;
import com.bohan.android.bakingapp.Data.LocalSource.RecipeContract;
import com.bohan.android.bakingapp.Data.LocalSource.StepContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * @author bohan
 */

public class DBUtilsCustomized {

    public static String querySelectAll(String tableName){
        return "SELECT * FROM " + tableName;
    }

    public static String querySelectById(String tableName, String column){
        return "SELECT * FROM " + tableName + " WHERE " + column + " = ?";
    }

    public static ContentValues recipesContentValues(@NonNull Recipe recipe){
        ContentValues recipesCV = new ContentValues();

        recipesCV.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_ID, recipe.id());
        recipesCV.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.name());
        recipesCV.put(RecipeContract.RecipeEntry.COLUMN_SERVINGS, recipe.servings());
        recipesCV.put(RecipeContract.RecipeEntry.COLUMN_IMAGE, recipe.image());

        return recipesCV;
    }

    public static ContentValues ingredientContentValues(@NonNull Ingredient ingredient,
                                                        int recipeId){
        ContentValues ingredientsCV = new ContentValues();

        ingredientsCV.put(IngredientContract.IngredientEntry.COLUMN_RECIPE_ID, recipeId);
        ingredientsCV.put(IngredientContract.IngredientEntry.COLUMN_QUANTITY, ingredient.quantity());
        ingredientsCV.put(IngredientContract.IngredientEntry.COLUMN_MEASURE, ingredient.measure());
        ingredientsCV.put(IngredientContract.IngredientEntry.COLUMN_INGREDIENT, ingredient.ingredient());

        return ingredientsCV;
    }

    public static ContentValues stepsContentValues(@NonNull Step step, int recipeId){
        ContentValues stepsCV = new ContentValues();

        stepsCV.put(StepContract.StepEntry.COLUMN_RECIPE_ID, recipeId);
        stepsCV.put(StepContract.StepEntry.COLUMN_STEP_ID, step.id());
        stepsCV.put(StepContract.StepEntry.COLUMN_SHORT_DESC, step.shortDescription());
        stepsCV.put(StepContract.StepEntry.COLUMN_DESC, step.description());
        stepsCV.put(StepContract.StepEntry.COLUMN_VIDEO_URL, step.videoURL());
        stepsCV.put(StepContract.StepEntry.COLUMN_THUMB_URL, step.thumbnailURL());

        return stepsCV;
    }

    public static List<Recipe> recipsCursor(@NonNull Cursor cursor){
        List<Recipe> recipes = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(-1);

            int recipeId;
            String recipeName;
            int recipeServings;
            String recipeImage;

            while (cursor.moveToNext()) {

                recipeId = cursor.getInt(cursor.getColumnIndexOrThrow(RecipeContract.RecipeEntry.COLUMN_RECIPE_ID));
                recipeName = cursor.getString(cursor.getColumnIndexOrThrow(RecipeContract.RecipeEntry.COLUMN_NAME));
                recipeServings = cursor.getInt(cursor.getColumnIndexOrThrow(RecipeContract.RecipeEntry.COLUMN_SERVINGS));
                recipeImage = cursor.getString(cursor.getColumnIndexOrThrow(RecipeContract.RecipeEntry.COLUMN_IMAGE));

                Recipe recipe = Recipe.builder()
                        .id(recipeId)
                        .name(recipeName)
                        .ingredients(new ArrayList<>())
                        .steps(new ArrayList<>())
                        .servings(recipeServings)
                        .image(recipeImage)
                        .build();

                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public static List<Ingredient> ingredientsCursor(@NonNull Cursor cursor) {

        List<Ingredient> ingredients = new ArrayList<>();


        if (cursor.getCount() > 0) {
            cursor.moveToPosition(-1);

            float ingredientQuantity;
            String ingredientMeasure;
            String ingredientName;

            while (cursor.moveToNext()) {

                ingredientQuantity = cursor.getFloat(cursor.getColumnIndexOrThrow(IngredientContract.IngredientEntry.COLUMN_QUANTITY));
                ingredientMeasure = cursor.getString(cursor.getColumnIndexOrThrow(IngredientContract.IngredientEntry.COLUMN_MEASURE));
                ingredientName = cursor.getString(cursor.getColumnIndexOrThrow(IngredientContract.IngredientEntry.COLUMN_INGREDIENT));

                Ingredient ingredient = Ingredient.builder()
                        .quantity(ingredientQuantity)
                        .measure(ingredientMeasure)
                        .ingredient(ingredientName)
                        .build();

                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    public static List<Step> stepsCursor(@NonNull Cursor cursor) {

        List<Step> steps = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(-1);

            int stepId;
            String stepShortDesc;
            String stepDesc;
            String stepVideo;
            String stepThumb;

            while (cursor.moveToNext()) {

                stepId = cursor.getInt(cursor.getColumnIndexOrThrow(StepContract.StepEntry.COLUMN_STEP_ID));
                stepShortDesc = cursor.getString(cursor.getColumnIndexOrThrow(StepContract.StepEntry.COLUMN_SHORT_DESC));
                stepDesc = cursor.getString(cursor.getColumnIndexOrThrow(StepContract.StepEntry.COLUMN_DESC));
                stepVideo = cursor.getString(cursor.getColumnIndexOrThrow(StepContract.StepEntry.COLUMN_VIDEO_URL));
                stepThumb = cursor.getString(cursor.getColumnIndexOrThrow(StepContract.StepEntry.COLUMN_THUMB_URL));

                Step step = Step.builder()
                        .id(stepId)
                        .shortDescription(stepShortDesc)
                        .description(stepDesc)
                        .videoURL(stepVideo)
                        .thumbnailURL(stepThumb)
                        .build();

                steps.add(step);
            }
        }

        return steps;
    }





}
