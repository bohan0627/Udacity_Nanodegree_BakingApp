package com.bohan.android.bakingapp;

/**
 * Created by Bo Han.
 */

import androidx.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicBoolean;
import androidx.annotation.Nullable;


public class RecipesIdlingResource implements IdlingResource {

    @Nullable
    private volatile IdlingResource.ResourceCallback callback;

    private AtomicBoolean isIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIdleState(boolean idleState) {
        isIdleNow.set(idleState);
        if (idleState && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}