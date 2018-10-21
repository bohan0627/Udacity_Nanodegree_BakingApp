package com.bohan.android.bakingapp.Data;

import android.net.LocalSocket;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.Data.LocalSource.LocalRetention;
import com.bohan.android.bakingapp.Data.RemoteSource.RemoteRetention;
import com.bohan.android.bakingapp.Utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RecipeRepo implements RecipeSource{

    private final RecipeSource localSource;
    private final RecipeSource remoteSource;

    @Inject
    public RecipeRepo(@LocalRetention RecipeSource localSource,
                      @RemoteRetention RecipeSource remoteSource){
        this.localSource = localSource;
        this.remoteSource = remoteSource;

    }
    @Override
    public Observable<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsById(int recipeId) {
        return localSource
                .getIngredientsById(recipeId)
                .compose(RxUtils.applySchedulers());
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsByName(String recipeName) {
        return localSource
                .getIngredientsByName(recipeName)
                .compose(RxUtils.applySchedulers());
    }

    @Override
    public Observable<List<Step>> getSteps(int recipeId) {
        return localSource
                .getSteps(recipeId)
                .compose(RxUtils.applySchedulers());
    }

    @Override
    public void storeRecipes(List<Recipe> recipes) {
        localSource.storeRecipes(recipes);
    }
}
