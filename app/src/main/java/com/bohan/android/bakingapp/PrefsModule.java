package com.bohan.android.bakingapp;

import android.content.Context;
//import android.support.annotation.NonNull;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;
@Module
public class PrefsModule {
    @Singleton
    @Provides
    @NonNull
    PrefsHelper providePreferencesHelper(@ApplicationContext Context context) {
        return new PrefsHelper(context);
    }

}