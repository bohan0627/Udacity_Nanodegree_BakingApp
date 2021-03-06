package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

/**
 * Created by Bo Han.
 */



import com.bohan.android.bakingapp.Data.RecipeRepo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RecipeStepsPresenter implements RecipeStepsContract.Presenter {
    private final RecipeRepo recipeRepository;
    private final RecipeStepsContract.View stepView;
    private final int recipeId;
    private final CompositeDisposable disposableList;

    @Inject
    RecipeStepsPresenter(RecipeRepo recipeRepository,
                         RecipeStepsContract.View stepView, int recipeId) {
        this.recipeRepository = recipeRepository;
        this.stepView = stepView;
        this.recipeId = recipeId;

        disposableList = new CompositeDisposable();
    }

    @Inject
    void setupListeners() {
        stepView.setBaseView(this);
    }

    @Override
    public void subscribe() {
        loadSteps();
    }

    @Override
    public void unsubscribe() {
        disposableList.clear();
    }

    @Override
    public void loadSteps() {
        Disposable subscription = recipeRepository
                .getSteps(recipeId)
                .subscribe(
                        steps -> {
                            stepView.showStepsInViewpager(steps);
                            stepView.move();
                        },
                        throwable -> stepView.errorMessage());

        disposableList.add(subscription);
    }
}

