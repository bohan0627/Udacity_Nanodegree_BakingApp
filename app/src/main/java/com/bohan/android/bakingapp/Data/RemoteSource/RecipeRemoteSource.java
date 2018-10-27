package com.bohan.android.bakingapp.Data.RemoteSource;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.Data.RecipeSource;
import com.bohan.android.bakingapp.MVP.Utils.RxUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import timber.log.Timber;
@Singleton
public class RecipeRemoteSource implements RecipeSource {

    private final RecipeRemoteService service;

    @Inject
    public RecipeRemoteSource(RecipeRemoteService service){
        this.service = service;
    }
    @Override
    public Observable<List<Recipe>> getRecipes() {
        return service
                .loardRecipesFromRemoteServer()
                .compose(RxUtils.applySchedulers())
                .doOnSubscribe(disposable -> Timber.d("Recipes Sync Started..."))
                .doOnError(throwable ->  Timber.d("Recipes Sync Failed!"))
                .doOnComplete(() -> Timber.d("Recipes Sync Completed."));
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsById(int recipeId) {
        throw new UnsupportedOperationException("getIngredientsById in RemoteDataSource is not implemented!");
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsByName(String recipeName) {
        throw new UnsupportedOperationException("getIngredientsByName in RemoteDataSource is not implemented!");
    }

    @Override
    public Observable<List<Step>> getSteps(int recipeId) {
        throw new UnsupportedOperationException("getSteps in RemoteDataSource is not implemented!");
    }

    @Override
    public void storeRecipes(List<Recipe> recipes) {
        throw new UnsupportedOperationException("storeRecipes in RemoteDataSource cannot be implemented!");
    }

}
