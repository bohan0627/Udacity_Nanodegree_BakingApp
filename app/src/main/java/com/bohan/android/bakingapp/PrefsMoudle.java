package com.bohan.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Provides;

public class PrefsMoudle {
    @Singleton
    @Provides
    @NonNull
    PrefsHelper providePreferencesHelper(@ApplicationContext Context context) {
        return new PrefsHelper(context);
    }

}