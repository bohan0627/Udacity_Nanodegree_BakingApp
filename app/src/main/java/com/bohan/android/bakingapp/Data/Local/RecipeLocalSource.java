package com.bohan.android.bakingapp.Data.Local;

/**
 * @author Bo Han
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.Data.source.RecipeSource;
import com.squareup.sqlbrite3.BriteDatabase;
//import com.squareup.sqlbrite3.BriteDatabase.Transaction;
//import hu.akarnokd.rxjava.interop.RxJavaInterop;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RecipeLocalSource implements RecipeSource {

    private final BriteDatabase briteDB;

    @Inject
    public RecipeLocalSource(BriteDatabase briteDatabase){
        this.briteDB = briteDatabase;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        Observable<List<Recipe>> recipesObservable = briteDB
                .createQuery(RecipeContract.RecipeEntry.TABLE_NAME,
                        DBUtilsCustomized.querySelectAll(RecipeContract.RecipeEntry.TABLE_NAME))
                .mapToOne(DBUtilsCustomized::recipsCursor);

        return recipesObservable;
    }


    @Override
    public Observable<List<Ingredient>> getIngredientsById(int recipeId) {
        Observable<List<Ingredient>> ingredientsObservable = briteDB
                .createQuery(IngredientContract.IngredientEntry.TABLE_NAME,
                        DBUtilsCustomized.querySelectById(IngredientContract.IngredientEntry.TABLE_NAME,
                                IngredientContract.IngredientEntry.COLUMN_RECIPE_ID),
                        String.valueOf(recipeId))
                .mapToOne(DBUtilsCustomized::ingredientsCursor);

        return ingredientsObservable;
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsByName(String recipeName) {
        return getRecipes()
                .flatMap(Observable::fromIterable)
                .filter(recipe -> Objects.equals(recipe.name(), recipeName))
                .map(Recipe::id)
                .flatMap(this::getIngredientsById);
    }

    @Override
    public Observable<List<Step>> getSteps(int recipeId) {
        Observable<List<Step>> stepsObservable = briteDB
                .createQuery(StepContract.StepEntry.TABLE_NAME,
                        DBUtilsCustomized.querySelectById(StepContract.StepEntry.TABLE_NAME,
                                StepContract.StepEntry.COLUMN_RECIPE_ID),
                        String.valueOf(recipeId))
                .mapToOne(DBUtilsCustomized::stepsCursor);

        return stepsObservable;
    }

    @Override
    public void storeRecipes(List<Recipe> recipes) {
        BriteDatabase.Transaction recipesTransaction = briteDB.newTransaction();

        try {
            deletePreviousRecipes();

            for (Recipe recipe : recipes) {

                int recipeId = recipe.id();

                for (Ingredient ingredient : recipe.ingredients()) {
                    briteDB.insert(IngredientContract.IngredientEntry.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT,
                            DBUtilsCustomized.ingredientContentValues(ingredient, recipeId));
                }

                for (Step step : recipe.steps()) {
                    briteDB.insert(StepContract.StepEntry.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT,
                            DBUtilsCustomized.stepsContentValues(step, recipeId));
                }

                briteDB.insert(RecipeContract.RecipeEntry.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT,
                        DBUtilsCustomized.recipesContentValues(recipe));
            }

            recipesTransaction.markSuccessful();

        } finally {
            recipesTransaction.end();
        }
    }

    private void deletePreviousRecipes(){
        briteDB.delete(RecipeContract.RecipeEntry.TABLE_NAME,null);
        briteDB.delete(IngredientContract.IngredientEntry.TABLE_NAME,null);
        briteDB.delete(StepContract.StepEntry.TABLE_NAME,null);
    }
}
