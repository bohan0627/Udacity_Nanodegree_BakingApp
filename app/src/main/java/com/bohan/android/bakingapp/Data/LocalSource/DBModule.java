package com.bohan.android.bakingapp.Data.LocalSource;

import android.content.Context;
//import android.support.annotation.NonNull;

import com.bohan.android.bakingapp.ApplicationContext;
import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import javax.inject.Singleton;


import androidx.sqlite.db.SupportSQLiteOpenHelper;
import dagger.Module;
import dagger.Provides;
import androidx.annotation.NonNull;
import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory;

import rx.Scheduler;
import rx.schedulers.Schedulers;

@Module
public class DBModule {
    @Singleton
    @Provides
    @NonNull
    BriteDatabase provideBriteDatabase(SqlBrite sqlBrite, DBHelper dbHelper, Scheduler scheduler) {
        SupportSQLiteOpenHelper.Configuration config = SupportSQLiteOpenHelper.Configuration.builder(context)
                .name("database-name.db")
                .callback(dbHelper)
                .build();
        SupportSQLiteOpenHelper helper = new RequerySQLiteOpenHelperFactory().create(config);
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, scheduler);
        return db;
    }

    @Singleton
    @Provides
    @NonNull
    SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder().build();
    }

    @Singleton
    @Provides
    @NonNull
    DBHelper provideDbHelper(@ApplicationContext Context context) {
        return new DBHelper(context);
    }

    @Provides
    @NonNull
    Scheduler provideScheduler() {
        return Schedulers.io();
    }
}
