package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

import com.bohan.android.bakingapp.Data.RecipeRepo;
//import com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails.RecipeDetailsContract;
import com.bohan.android.bakingapp.RecipesIdlingResource;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RecipeListPresenter implements RecipeListContract.Presenter {

    private final RecipeRepo recipeRepository;
    private final RecipeListContract.View recipesView;
    private final CompositeDisposable disposableList;

    @Inject
    RecipeListPresenter(RecipeRepo recipeRepository,
                        RecipeListContract.View recipesView) {
        this.recipeRepository = recipeRepository;
        this.recipesView = recipesView;

        disposableList = new CompositeDisposable();
    }

    @Inject
    void setupListeners() {
        recipesView.setBaseView(this);
    }

    @Override
    public void subscribe() {
        loadRecipesFromRepo(false, null);
    }

    @Override
    public void unsubscribe() {
        disposableList.clear();
    }

    @Override
    public void loadRecipesFromRepo(boolean forcedSync, RecipesIdlingResource resource) {

        if (forcedSync) {
            recipeRepository.markRepoAsSynced(false);
        }

        disposableList.clear();

        Disposable subscription = recipeRepository
                .getRecipes()
                .doOnSubscribe(disposable -> {
                    recipesView.showLoadingIndicator(true);
                    if (resource != null) resource.setIdleState(false);
                })
                .subscribe(
                        //OnNext
                        recipeList -> {
                            recipesView.showRecipeList(recipeList);
                            recipeRepository.markRepoAsSynced(true);
                            recipesView.showLoadingIndicator(false);
                            if (resource != null) resource.setIdleState(true);
                            if (forcedSync) recipesView.showCompletedMessage();
                        },
                        // OnError
                        throwable -> {
                            recipesView.showLoadingIndicator(false);
                            recipesView.showErrorMessage();
                            recipeRepository.markRepoAsSynced(false);
                        });

        disposableList.add(subscription);
    }

    @Override
    public void openRecipeDetails(int recipeId) {
        recipesView.showRecipeDetails(recipeId);
    }
}
