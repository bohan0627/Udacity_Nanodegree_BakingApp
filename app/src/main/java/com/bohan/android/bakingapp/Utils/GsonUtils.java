package com.bohan.android.bakingapp.Utils;

/**
 * Created by Bo Han.
 */

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class GsonUtils implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_GsonUtils();
    }
}
