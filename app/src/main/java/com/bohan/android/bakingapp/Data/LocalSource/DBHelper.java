package com.bohan.android.bakingapp.Data.LocalSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

public class DBHelper extends SupportSQLiteOpenHelper.Callback {

    private static final String DATABASE_NAME = "bakingRecipes.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Creates a new Callback to get database lifecycle events.
     *
     * @param version The version for the database instance. See {@link #version}.
     */
    public DBHelper(int version) {
        super(version);
    }



    @Override
    public void onCreate(SupportSQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(IngredientContract.SQL_QUERY_CREATE);
        sqLiteDatabase.execSQL(StepContract.SQL_QUERY_CREATE);
        sqLiteDatabase.execSQL(RecipeContract.SQL_QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SupportSQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StepContract.StepEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IngredientContract.IngredientEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
