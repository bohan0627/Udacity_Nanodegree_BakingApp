package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

import android.content.Context;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;

import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.R;

import java.util.List;
import java.util.Locale;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class RecipeStepsAdapter extends FragmentPagerAdapter {

    private List<Step> steps;
    private final String tabTitle;

    RecipeStepsAdapter(FragmentManager fm, List<Step> steps, Context context) {
        super(fm);
        setSteps(steps);
        tabTitle = context.getResources().getString(R.string.recipe_step_tab_label);
    }

    public void setSteps(@NonNull List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeStepSingleFragment.newInstance(
                steps.get(position).description(),
                steps.get(position).videoURL(),
                steps.get(position).thumbnailURL()
        );
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(Locale.US, tabTitle, position);
    }
}
