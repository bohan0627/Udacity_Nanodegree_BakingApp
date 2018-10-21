package com.bohan.android.bakingapp;

import android.content.Context;

import com.bohan.android.bakingapp.Data.RemoteSource.RecipeRemoteService;

import javax.inject.Singleton;

import dagger.Provides;

public class BakingModule {
    private final Context context;

    public BakingModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    RecipeRemoteService provideRecipeService() {
        return ServiceFactory.createFrom(RecipeService.class, RecipeService.ENDPOINT);
    }
}

