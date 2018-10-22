package com.bohan.android.bakingapp.Data.RemoteSource;

import com.bohan.android.bakingapp.Data.LocalSource.LocalRetention;
import com.bohan.android.bakingapp.Data.LocalSource.RecipeLocalSource;
import com.bohan.android.bakingapp.Data.RecipeSource;
import com.squareup.sqlbrite3.BriteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RecipeRepoModule {

    @Singleton
    @Provides
    @LocalRetention
    RecipeSource provideRecipeLocalDataSource(BriteDatabase briteDatabase) {
        return new RecipeLocalSource(briteDatabase);
    }

    @Singleton
    @Provides
    @RemoteRetention
    RecipeSource provideRecipeRemoteDataSource(RecipeRemoteService service) {
        return new RecipeRemoteSource(service);
    }
}
