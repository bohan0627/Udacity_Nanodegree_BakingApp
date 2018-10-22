package com.bohan.android.bakingapp;

import android.app.Application;

import com.bohan.android.bakingapp.Data.DaggerRecipeRepoComponent;
import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
//import com.bohan.android.bakingapp.Data.DaggerRecipeRepoComponent;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
//import com.facebook.stetho.Stetho;
//import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;
/**
 * This class is partially aimed to analysis memory leak
 */
public class BakingApp extends Application {
    private RecipeRepoComponent recipeRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {

            return;
        }
        LeakCanary.install(this);
        //Baking app initilizing here

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());

            Stetho.initializeWithDefaults(this);
        }

        recipeRepositoryComponent = DaggerRecipeRepoComponent.builder()
                .bakingModule(new BakingModule(getApplicationContext()))
                .build();
    }

    public RecipeRepoComponent getRecipeRepositoryComponent() {
        return recipeRepositoryComponent;
    }
}
