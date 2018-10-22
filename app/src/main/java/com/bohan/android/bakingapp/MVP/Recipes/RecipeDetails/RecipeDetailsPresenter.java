package com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails;

import com.bohan.android.bakingapp.Data.RecipeRepo;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

class RecipeDetailsPresenter implements RecipeDetailsContract.Presenter {

    private final RecipeRepo recipeRepository;
    private final RecipeDetailsContract.View detailsView;
    private final int recipeId;
    private final CompositeDisposable disposableList;

    @Inject
    RecipeDetailsPresenter(
            RecipeRepo recipeRepository,
            RecipeDetailsContract.View detailsView, int recipeId) {
        this.recipeRepository = recipeRepository;
        this.detailsView = detailsView;
        this.recipeId = recipeId;

        disposableList = new CompositeDisposable();
    }

    @Inject
    void setupListeners() {
        detailsView.setBaseView(this);
    }

    @Override
    public void subscribe() {
        loadRecipeNameFromRepo();
        loadIngredientsFromRepo();
        loadStepsFromRepo();
    }

    @Override
    public void unsubscribe() {
        disposableList.clear();
    }

    @Override
    public void loadRecipeNameFromRepo() {
        Disposable subscription = recipeRepository
                .getRecipes()
                .flatMap(Observable::fromIterable)
                .filter(recipe -> recipe.id() == recipeId)
                .subscribe(
                        // OnNext
                        recipe -> detailsView.recipeName(recipe.name()),
                        // OnError
                        throwable -> detailsView.errorMessage());

        disposableList.add(subscription);
    }

    @Override
    public void loadIngredientsFromRepo() {

        Disposable subscription = recipeRepository
                .getIngredientsById(recipeId)
                .subscribe(
                        // OnNext
                        detailsView::ingredientsList,
                        // OnError
                        throwable -> detailsView.errorMessage());

        disposableList.add(subscription);
    }

    @Override
    public void loadStepsFromRepo() {

        Disposable subscription = recipeRepository
                .getSteps(recipeId)
                .subscribe(
                        // OnNext
                        detailsView::stepsList,
                        // OnError
                        throwable -> detailsView.errorMessage());

        disposableList.add(subscription);
    }

    @Override
    public void openStepDetails(int stepId) {
        detailsView.stepDetails(stepId);
    }

    @Override
    public void fetchStepData(int stepId) {

        Disposable subscription = recipeRepository
                .getSteps(recipeId)
                .flatMap(Observable::fromIterable)
                .filter(step -> step.id() == stepId)
                .subscribe(
                        // OnNext
                        step ->
                                detailsView.refreshStepContainerFragment(
                                        step.description(),
                                        step.videoURL(),
                                        step.thumbnailURL()),
                        // OnError
                        throwable -> detailsView.errorMessage());

        disposableList.add(subscription);
    }
}
