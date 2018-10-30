package com.bohan.android.bakingapp.MVP.widget;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.MVP.BakingModule;
import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.MVP.Utils.ProviderScoped;

import dagger.Component;

@ProviderScoped
@Component(dependencies = RecipeRepoComponent.class, modules = BakingModule.class)
interface WidgetHelperComponent {

    void inject(WidgetProvider provider);
    void inject(WidgetConfigActivity activity);
}
