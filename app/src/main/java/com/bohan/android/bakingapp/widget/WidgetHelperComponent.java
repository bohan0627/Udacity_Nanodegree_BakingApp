package com.bohan.android.bakingapp.widget;

import com.bohan.android.bakingapp.Data.RecipeRepo;

import dagger.Component;

@ProviderScoped
@Component(dependencies = RecipeRepo.class, modules = BakingAppModule.class)
interface WidgetDataHelperComponent {

    void inject(WidgetProvider provider);
    void inject(WidgetConfigActivity activity);
}
