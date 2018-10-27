package com.bohan.android.bakingapp;

/**
 * Created by Bo Han.
 */

import android.content.Context;
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