package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.MVP.Recipes.RecipeList.RecipeListActivity;

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
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;

/**
 * Created by Bo Han.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityIntentTest {

    private static final String EXTRA_RECIPE_ID_KEY = "RECIPE_ID";
    private static final int EXTRA_RECIPE_ID_VALUE = 1;

    @Rule
    public IntentsTestRule<RecipeListActivity> intentsTestRule
            = new IntentsTestRule<>(RecipeListActivity.class);

    @Test
    public void clickOnRecyclerViewItem_runsRecipeDetailsActivityIntent() {

        onView(ViewMatchers.withId(R.id.recipe_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(
                hasExtra(EXTRA_RECIPE_ID_KEY, EXTRA_RECIPE_ID_VALUE)
        );
    }

}
