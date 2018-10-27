package com.bohan.android.bakingapp.MVP.Utils.Prefs;

/**
 * Created by Bo Han.
 */

import android.content.Context;

import com.bohan.android.bakingapp.MVP.Utils.ApplicationContext;

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