package com.bohan.android.bakingapp.widget;

/**
 * Created by Bo Han.
 */

import com.bohan.android.bakingapp.BakingModule;
import com.bohan.android.bakingapp.Data.RecipeRepoComponent;
import com.bohan.android.bakingapp.Utils.ProviderScoped;

import dagger.Component;

@ProviderScoped
@Component(dependencies = RecipeRepoComponent.class, modules = BakingModule.class)
interface WidgetDataHelperComponent {

    void inject(WidgetProvider provider);
    void inject(WidgetConfigActivity activity);
}
