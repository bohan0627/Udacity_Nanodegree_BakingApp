package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

/**
 * Created by Bo Han.
 */

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

public class RecipeStepsFragment extends Fragment implements RecipeStepsContract.View {

    @BindView(R.id.recipe_step_viewpager)
    ViewPager recipeStepViewPager;
    @BindView(R.id.recipe_step_tablayout)
    TabLayout recipeStepTabLayout;

    @BindString(R.string.loading_data_error)
    String errorMessage;
    @BindBool(R.bool.two_pane_mode)
    boolean isTwoPane;

    Unbinder unbinder;

    private RecipeStepsContract.Presenter recipeStepPresenter;
    private RecipeStepsAdapter viewPagerAdapter;

    int stepId;

    public RecipeStepsFragment() {
    }

    public static RecipeStepsFragment newInstance(int stepId) {
        Bundle arguments = new Bundle();
        arguments.putInt(RecipeStepsActivity.EXTRA_STEP_ID, stepId);
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            stepId = getArguments().getInt(RecipeStepsActivity.EXTRA_STEP_ID);
        } else {
            stepId = savedInstanceState.getInt(RecipeStepsActivity.EXTRA_STEP_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        unbinder = ButterKnife.bind(this, view);

        viewPagerAdapter = new RecipeStepsAdapter(getFragmentManager(), new ArrayList<>(0), getContext());
        recipeStepViewPager.setAdapter(viewPagerAdapter);
        setUpViewPagerListener();
        recipeStepTabLayout.setupWithViewPager(recipeStepViewPager);

        // Hide tabs on landscape not-twoPane mode
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTwoPane) {
            recipeStepTabLayout.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(RecipeStepsActivity.EXTRA_STEP_ID, stepId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        recipeStepPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        recipeStepPresenter.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setBaseView(RecipeStepsContract.Presenter presenter) {
        this.recipeStepPresenter = presenter;
    }

    @Override
    public void showStepsInViewpager(List<Step> steps) {
        viewPagerAdapter.setSteps(steps);
    }

    @Override
    public void errorMessage() {
        // User should not see this
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void move() {
        recipeStepViewPager.setCurrentItem(stepId);
    }

    private void setUpViewPagerListener() {
        recipeStepViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                stepId = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}

