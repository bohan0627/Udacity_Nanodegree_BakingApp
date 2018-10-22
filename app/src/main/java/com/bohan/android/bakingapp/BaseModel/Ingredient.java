package com.bohan.android.bakingapp.BaseModel;

import android.net.Uri;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import com.google.gson.Gson;

@AutoValue
public abstract class Ingredient {
    public abstract float quantity();
    public abstract String measure();
    public abstract String ingredient();

    public static Builder builder(){
        return new AutoValue_Ingredient.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder{
        public abstract Builder quantity(float quantity);
        public abstract Builder measure(String measure);
        public abstract Builder ingredient(String ingredient);

        public abstract Ingredient build();

    }

    public static TypeAdapter<Ingredient> typeAdapter(Gson gson) {
        return new AutoValue_Ingredient.GsonTypeAdapter(gson);
    }
}
