package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails.RecipeDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Bo Han.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailsActivityUITest {

    private static final int STEP_WITH_VIDEO = 0;
    private static final int STEP_WITHOUT_VIDEO = 1;

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> activityActivityTestRule =
            new ActivityTestRule<>(RecipeDetailsActivity.class);

    @Test
    public void clickOnRecyclerViewItem_opensRecipeStepActivity() {

        onView(withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.recipe_step_viewpager))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnStepWithVideo_showsVideoPlayerView() {

        onView(withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(STEP_WITH_VIDEO, click()));

        onView(
                allOf(
                        withId(R.id.recipe_step_video),
                        withParent(withParent(withId(R.id.recipe_step_viewpager))),
                        isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnStepWithoutVideo_hidesVideoPlayerView() {

        onView(withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(STEP_WITHOUT_VIDEO, click()));

        onView(
                allOf(
                        withId(R.id.recipe_step_video),
                        withParent(withParent(withId(R.id.recipe_step_viewpager))),
                        isDisplayed()))
                .check(doesNotExist());
    }
}
