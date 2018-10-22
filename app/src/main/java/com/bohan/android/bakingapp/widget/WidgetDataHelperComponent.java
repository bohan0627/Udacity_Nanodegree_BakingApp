package com.bohan.android.bakingapp.widget;

import com.bohan.android.bakingapp.BakingModule;
import com.bohan.android.bakingapp.Data.RecipeRepo;
import com.bohan.android.bakingapp.Utils.ProviderScoped;

import dagger.Component;

@ProviderScoped
@Component(dependencies = RecipeRepo.class, modules = BakingModule.class)
interface WidgetDataHelperComponent {

    void inject(WidgetProvider provider);
    void inject(WidgetConfigActivity activity);
}
