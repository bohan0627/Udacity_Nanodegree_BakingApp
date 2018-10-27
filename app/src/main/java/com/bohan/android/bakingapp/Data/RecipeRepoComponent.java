package com.bohan.android.bakingapp.Data;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.MVP.BakingModule;
import com.bohan.android.bakingapp.Data.LocalSource.DBModule;
import com.bohan.android.bakingapp.MVP.Utils.Prefs.PrefsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RecipeRepoModule.class, DBModule.class, PrefsModule.class,
        BakingModule.class})
public interface RecipeRepoComponent {

    RecipeRepo getRecipeRepository();
}
