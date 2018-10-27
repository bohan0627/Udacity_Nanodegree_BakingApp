package com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails;

/**
 * Created by Bo Han.
 */

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.bohan.android.bakingapp.BaseModel.Ingredient;
import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps.RecipeStepSingleFragment;
import com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps.RecipeStepsActivity;
import com.bohan.android.bakingapp.R;
import com.bohan.android.bakingapp.MVP.Utils.FragmentUtils;
import com.bohan.android.bakingapp.MVP.Utils.StringUtils;
import com.bohan.android.bakingapp.MVP.Utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsContract.View{

    @BindView(R.id.recipe_details_ingredients)
    TextView recipeDetailsIngredients;
    @BindView(R.id.recipe_details_steps)
    RecyclerView recipeDetailsRecyclerView;

    @BindBool(R.bool.two_pane_mode)
    boolean twoPaneMode;
    @BindString(R.string.loading_data_error)
    String errorMessage;
    @BindString(R.string.recipe_details_ingredients_header)
    String ingredientsListHeader;

    Unbinder unbinder;

    private RecipeDetailsContract.Presenter recipeDetailsPresenter;
    private RecipeDetailsAdapter recipeDetailsAdapter;
    private int recipeId;

    public RecipeDetailsFragment() {
    }

    public static RecipeDetailsFragment newInstance(int recipeId) {
        Bundle arguments = new Bundle();
        arguments.putInt(RecipeDetailsActivity.EXTRA_RECIPE_ID, recipeId);
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getArguments().getInt(RecipeDetailsActivity.EXTRA_RECIPE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        recipeDetailsAdapter = new RecipeDetailsAdapter(new ArrayList<>(0),
                stepId -> recipeDetailsPresenter.openStepDetails(stepId));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recipeDetailsRecyclerView.setLayoutManager(layoutManager);
        recipeDetailsRecyclerView.setHasFixedSize(true);
        recipeDetailsRecyclerView.setAdapter(recipeDetailsAdapter);

        recipeDetailsRecyclerView
                .addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        if (twoPaneMode) {
            recipeDetailsPresenter.fetchStepData(0);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recipeDetailsPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        recipeDetailsPresenter.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void ingredientsList(List<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();
        sb.append(ingredientsListHeader);

        for (Ingredient ingredient : ingredients) {

            String name = ingredient.ingredient();
            float quantity = ingredient.quantity();
            String measure = ingredient.measure();

            sb.append("\n");
            sb.append(StringUtils.formatIngdedient(getContext(), name, quantity, measure));
        }

        TextViewUtils.setTextWithSpan(recipeDetailsIngredients, sb.toString(), ingredientsListHeader,
                new StyleSpan(Typeface.BOLD));
    }

    @Override
    public void stepsList(List<Step> steps) {
        recipeDetailsAdapter.refreshStepsList(steps);
    }

    @Override
    public void errorMessage() {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void recipeName(String recipeName) {
        getActivity().setTitle(recipeName);
    }

    @Override
    public void stepDetails(int stepId) {
        if (twoPaneMode) {
            recipeDetailsPresenter.fetchStepData(stepId);
        } else {
            startActivity(RecipeStepsActivity.prepareIntent(getContext(), recipeId, stepId));
        }
    }

    @Override
    public void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl) {

        RecipeStepSingleFragment fragment =
                RecipeStepSingleFragment.newInstance(desc, videoUrl, imageUrl);

        FragmentUtils.replaceFragment(
                getChildFragmentManager(),
                fragment,
                R.id.recipe_step_container);
    }

    @Override
    public void setBaseView(RecipeDetailsContract.Presenter baseView) {
        this.recipeDetailsPresenter = baseView;
    }
}