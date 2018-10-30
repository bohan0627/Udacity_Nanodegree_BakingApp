package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.MVP.Recipes.RecipeDetails.RecipeDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.CoreMatchers.allOf;
import static androidx.test.espresso.intent.Intents.intended;

/**
 * Created by Bo Han.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailsActivityIntentTest {
    private static final String EXTRA_RECIPE_ID_KEY = "RECIPE_ID";
    private static final int EXTRA_RECIPE_ID_VALUE = 1;

    private static final String EXTRA_STEP_ID_KEY = "STEP_ID";
    private static final int EXTRA_STEP_ID_VALUE = 1;

    @Rule
    public IntentsTestRule<RecipeDetailsActivity> intentsTestRule
            = new IntentsTestRule<>(RecipeDetailsActivity.class);

    @Test
    public void clickOnRecyclerViewItem_runsRecipeStepActivityIntent() {

        onView(ViewMatchers.withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        intended(allOf(
                hasExtra(EXTRA_RECIPE_ID_KEY, EXTRA_RECIPE_ID_VALUE),
                hasExtra(EXTRA_STEP_ID_KEY, EXTRA_STEP_ID_VALUE)
        ));
    }
}
