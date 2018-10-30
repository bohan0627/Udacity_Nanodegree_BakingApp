package com.bohan.android.bakingapp;

import com.bohan.android.bakingapp.MVP.Recipes.RecipeList.RecipeListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Bo Han.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityUITest {

    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource() {
        idlingResource = activityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void clickOnRefreshMenuItem_showsSuccessfulSyncMessage() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_refresh))
                .perform(click());

        onView(withText(R.string.recipe_list_sync_completed)).
                inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void clickOnRecyclerViewItem_opensRecipeDetailsActivity() {

        onView(withId(R.id.recipe_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.recipe_details_ingredients))
                .check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
