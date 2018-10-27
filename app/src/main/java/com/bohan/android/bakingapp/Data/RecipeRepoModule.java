package com.bohan.android.bakingapp.Data;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.Data.LocalSource.LocalRetention;
import com.bohan.android.bakingapp.Data.LocalSource.RecipeLocalSource;
import com.bohan.android.bakingapp.Data.RemoteSource.RecipeRemoteService;
import com.bohan.android.bakingapp.Data.RemoteSource.RecipeRemoteSource;
import com.bohan.android.bakingapp.Data.RemoteSource.RemoteRetention;
import com.squareup.sqlbrite2.BriteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeRepoModule {

    @Singleton
    @Provides
    @LocalRetention
    RecipeSource getRecipeLocalSource(BriteDatabase briteDatabase) {
        return new RecipeLocalSource(briteDatabase);
    }

    @Singleton
    @Provides
    @RemoteRetention
    RecipeSource getRecipeRemoteDataSource(RecipeRemoteService service) {
        return new RecipeRemoteSource(service);
    }

}
