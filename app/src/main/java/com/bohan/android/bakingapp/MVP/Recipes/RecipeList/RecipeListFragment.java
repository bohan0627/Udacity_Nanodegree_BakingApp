package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

/**
 * Created by Bo Han.
 */

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails.RecipeDetailsActivity;
import com.bohan.android.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class RecipeListFragment extends Fragment implements RecipeListContract.View {

    private static final String SAVED_SCROLL_POSITION = "SAVED_SCROLL_POSITION";

    @BindView(R.id.recipe_list_recycler_view)
    RecyclerView recipeListRecyclerView;
    @BindView(R.id.recipe_list_progress_bar)
    ProgressBar recipeListProgressBar;

    @BindInt(R.integer.grid_column_count)
    int gridColumnCount;
    @BindString(R.string.recipe_list_sync_completed)
    String syncCompletedMessage;
    @BindString(R.string.recipe_list_connection_error)
    String connectionErrorMessage;

    Unbinder unbinder;

    private RecipeListContract.Presenter recipeListPresenter;
    private RecipeListAdapter recipeListAdapter;
    private GridLayoutManager layoutManager;

    public RecipeListFragment() {
    }

    public static RecipeListFragment newInstance() {
        return new RecipeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        recipeListAdapter = new RecipeListAdapter(
                new ArrayList<>(0),
                recipeId -> recipeListPresenter.openRecipeDetails(recipeId)
        );

        recipeListAdapter.setHasStableIds(true);

        layoutManager = new GridLayoutManager(getContext(), gridColumnCount);
        recipeListRecyclerView.setLayoutManager(layoutManager);
        recipeListRecyclerView.setHasFixedSize(true);
        recipeListRecyclerView.setAdapter(recipeListAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_SCROLL_POSITION)) {
            int position = savedInstanceState.getInt(SAVED_SCROLL_POSITION);
            new Handler().postDelayed(() -> layoutManager.scrollToPositionWithOffset(position, 0), 200);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int position = layoutManager.findLastVisibleItemPosition();
        outState.putInt(SAVED_SCROLL_POSITION, position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        recipeListPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        recipeListPresenter.unsubscribe();
    }

    @Override
    public void setBaseView(@NonNull RecipeListContract.Presenter presenter) {
        this.recipeListPresenter = presenter;
    }

    @Override
    public void showRecipeList(List<Recipe> recipeList) {
        recipeListAdapter.updateRecipeList(recipeList);
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        setViewVisibility(recipeListRecyclerView, !show);
        setViewVisibility(recipeListProgressBar, show);
    }

    @Override
    public void showCompletedMessage() {
        Toast.makeText(getContext(), syncCompletedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getContext(), connectionErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRecipeDetails(int recipeId) {
        startActivity(RecipeDetailsActivity.prepareIntent(getContext(), recipeId));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setViewVisibility(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
