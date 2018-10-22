package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

import android.content.Context;

import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.R;

import java.util.List;
import java.util.Locale;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
@Module
public class RecipeStepsPresenterModule {
    private final RecipeStepsContract.View view;
    private final int recipeId;

    RecipeStepsPresenterModule(RecipeStepsContract.View view, int recipeId) {
        this.view = view;
        this.recipeId = recipeId;
    }

    @Provides
    RecipeStepsContract.View provideRecipeStepContractView() {
        return view;
    }

    @Provides
    int provideRecipeId() {
        return recipeId;
    }
}
