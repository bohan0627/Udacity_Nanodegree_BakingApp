package com.bohan.android.bakingapp.MVP.Recipes.RecipeList;

/**
 * Created by Bo Han.
 */

import android.view.LayoutInflater;
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

    private List<Recipe> recipeList;
    final OnRecipeClickListener recipeClickListener;

    RecipeListAdapter(List<Recipe> recipes, OnRecipeClickListener recipeClickListener) {
        setRecipes(recipes);
        this.recipeClickListener = recipeClickListener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe_list_item, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bindTo(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public long getItemId(int position) {
        return recipeList.get(position).id();
    }

    void updateRecipeList(List<Recipe> recipes) {
        setRecipes(recipes);
        notifyDataSetChanged();
    }

    private void setRecipes(@NonNull List<Recipe> recipes) {
        recipeList = recipes;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.list_recipe_name)
        TextView recipeName;

        @BindView(R.id.list_recipe_servings)
        TextView recipeServings;

        @BindString(R.string.recipe_list_servings_text)
        String servingsText;

        private int curId;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        void bindTo(@NonNull Recipe recipe) {

            curId = recipe.id();

            String name = recipe.name();
            recipeName.setText(name);
            int servings = recipe.servings();
            recipeServings.setText(String.format(Locale.US, servingsText, servings));
        }

        @Override
        public void onClick(View v) {
            recipeClickListener.recipeClicked(curId);
        }
    }

    interface OnRecipeClickListener {

        void recipeClicked(int recipeId);
    }
}
