package com.bohan.android.bakingapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bohan.android.bakingapp.BaseModel.Step;

import java.util.List;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class RecipeStepsAdapter extends FragmentPagerAdapter {

    private final String title;
    private List<Step> steps;

    public RecipeStepsAdapter(Context context, List<Step> steps, FragmentManager manager){
        super(manager);
        title = "Step: ${count}";
        setSteps(steps);
    }

    @Override
    public Fragment getItem(int stepPosition) {
        Fragment item = RecipeStepSingleFragment.newInstance(
                steps.get(stepPosition).description(),
                steps.get(stepPosition).videoURL(),
                steps.get(stepPosition).thumbnailURL());

        return item;
    }

    public void setSteps(@NonNull List<Step> steps){
        this.steps = steps;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(Locale.US, title, position);
    }
}
