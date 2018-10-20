package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.BaseModel.Step;

import java.util.List;

/**
 * Two interfaces included in this Contract: StepsView and StepsPresenter that both extends from base interface
 */
public interface RecipeStepsContract {


    interface StepsView extends BaseView<RecipeDetailsContract.Presenter> {

        void showStepsInViewpager(List<Step> steps);

        void errorMessage();

        void move();
    }

    /**
     * This method is for moving steps to associated Adapter
     */
    interface StepsPresenter extends BasePresenter {

        void loadSteps();
    }
}
