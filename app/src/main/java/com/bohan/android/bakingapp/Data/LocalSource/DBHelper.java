package com.bohan.android.bakingapp.Data.LocalSource;

import android.content.Context;
//import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.sqlite.db.SupportSQLiteDatabase;
//import androidx.sqlite.db.SupportSQLiteOpenHelper;
//import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "bakingRecipes.db";
    private static final int DATABASE_VERSION = 1;

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(IngredientContract.SQL_QUERY_CREATE);
        sqLiteDatabase.execSQL(StepContract.SQL_QUERY_CREATE);
        sqLiteDatabase.execSQL(RecipeContract.SQL_QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StepContract.StepEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IngredientContract.IngredientEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
