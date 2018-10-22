package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.MVP.BasePresenter;
import com.bohan.android.bakingapp.MVP.BaseView;
import com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails.RecipeDetailsContract;

import java.util.List;

/**
 * Two interfaces included in this Contract: StepsView and StepsPresenter that both extends from base interface
 */
public interface RecipeStepsContract {


    interface View extends BaseView<Presenter> {

        void showStepsInViewpager(List<Step> steps);

        void errorMessage();

        void move();
    }

    /**
     * This method is for moving steps to associated Adapter
     */
    interface Presenter extends BasePresenter {

        void loadSteps();
    }
}
