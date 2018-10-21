package com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bohan.android.bakingapp.BaseModel.Step;
import com.bohan.android.bakingapp.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.StepsViewHolder> {

    private List<Step> steps;
    final OnStepClickListener recipeListener;
    int curPosition;

    RecipeDetailsAdapter(List<Step> steps, OnStepClickListener listener){
        this.steps = steps;
        recipeListener = listener;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View stepView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe_details_item, parent, false);

        return new StepsViewHolder(stepView);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.bindTo(steps.get(position), position);
    }

    @Override
    public long getItemId(int position) {
        return steps.get(position).id();;
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    private void setSteps(@NonNull List<Step> steps) {
        this.steps = steps;
    }

    public void refreshStepsList(List<Step> steps) {
        setSteps(steps);
        notifyDataSetChanged();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.list_step_layout)
        RelativeLayout stepItemLayout;
        @BindView(R.id.list_step_description)
        TextView stepDescription;
        @BindView(R.id.list_step_video_icon)
        ImageView videoIcon;
        @BindColor(R.color.colorGrayBackground)
        int normalItemBackground;
        @BindColor(R.color.colorPrimaryLight)
        int currentItemBackground;

        private int curId;

        StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            curPosition = curId;
            recipeListener.stepClicked(curId);
            notifyDataSetChanged();
        }

        void bindTo(@NonNull Step step, int bindPosition) {

            currentId = step.id();

            String description = step.shortDescription();
            stepDescription.setText(String.format(Locale.US, "%d. %s", currentId, description));

            String video = step.videoURL();

            if (video.isEmpty()) {
                videoIcon.setVisibility(View.INVISIBLE);
            } else {
                videoIcon.setVisibility(View.VISIBLE);
            }

            if (currentPos == bindPosition) {
                stepItemLayout.setBackgroundColor(currentItemBackground);
            } else {
                stepItemLayout.setBackgroundColor(normalItemBackground);
            }
        }
    }

    interface OnStepClickListener {

        void stepClicked(int stepId);
    }
}
