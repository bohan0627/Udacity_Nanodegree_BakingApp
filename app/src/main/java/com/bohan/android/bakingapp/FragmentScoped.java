package com.bohan.android.bakingapp;

/**
 * Created by Bo Han.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {

}