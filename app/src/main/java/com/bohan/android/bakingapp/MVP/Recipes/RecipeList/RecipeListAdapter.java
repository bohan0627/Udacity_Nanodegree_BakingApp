package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bohan.android.bakingapp.BaseModel.Recipe;
import com.bohan.android.bakingapp.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private List<Recipe> recipesList;
    private final RecipesOnClickListener recipesListenter;

    RecipeListAdapter(List<Recipe> recipes, RecipesOnClickListener listener) {
        setRecipes(recipes);
        recipesListenter = listener;
    }

    public interface RecipesOnClickListener {

        void recipeClicked(int recipeId);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {

    }

    @Override
    public long getItemId(int position) {
        return recipesList.get(position).id();
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    private void setRecipes(@NonNull List<Recipe> recipes) {
        recipesList = recipes;
    }

    public void updateStepsList(List<Recipe> recipes) {
        setRecipes(recipes);
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.list_recipe_name)
        TextView recipeName;

        @BindView(R.id.list_recipe_servings)
        TextView recipeServings;

        @BindString(R.string.recipe_list_servings_text)
        String servingsText;

        private int currentId;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bindTo(@NonNull Recipe recipe) {

            currentId = recipe.id();

            String name = recipe.name();
            recipeName.setText(name);
            int servings = recipe.servings();
            recipeServings.setText(String.format(Locale.US, servingsText, servings));
        }

        @Override
        public void onClick(View v) {
            recipesListenter.recipeClicked(currentId);
        }
    }

}
