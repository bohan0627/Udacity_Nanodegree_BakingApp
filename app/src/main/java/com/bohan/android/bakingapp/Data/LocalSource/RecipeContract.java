package com.bohan.android.bakingapp.Data.LocalSource;

public class RecipeContract {
    public static abstract class RecipeEntry {

        public static final String TABLE_NAME = "recipes";
        public static final String COLUMN_RECIPE_ID = "recipeId";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SERVINGS = "servings";
        public static final String COLUMN_IMAGE = "image";
    }

    static final String SQL_QUERY_CREATE =
            "CREATE TABLE " + RecipeEntry.TABLE_NAME + " ("
                    + RecipeEntry.COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY,"
                    + RecipeEntry.COLUMN_NAME + " TEXT NOT NULL,"
                    + RecipeEntry.COLUMN_SERVINGS + " INTEGER NOT NULL,"
                    + RecipeEntry.COLUMN_IMAGE + " TEXT NOT NULL"
                    + ")";
}
