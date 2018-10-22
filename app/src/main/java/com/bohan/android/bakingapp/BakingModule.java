package com.bohan.android.bakingapp;

import android.content.Context;

import com.bohan.android.bakingapp.Data.RemoteSource.RecipeRemoteService;
import com.bohan.android.bakingapp.Data.RemoteSource.ServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
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
        return ServiceFactory.createFrom(RecipeRemoteService.class, RecipeRemoteService.BASE_URL);
    }
}

