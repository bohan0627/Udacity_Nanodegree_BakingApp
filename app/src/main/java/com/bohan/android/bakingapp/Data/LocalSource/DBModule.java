package com.bohan.android.bakingapp.Data.LocalSource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import javax.inject.Singleton;

import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class DBModule {
    @Singleton
    @Provides
    @NonNull
    BriteDatabase provideBriteDatabase(SqlBrite sqlBrite, DbHelper dbHelper, Scheduler scheduler) {
        return sqlBrite.wrapDatabaseHelper(dBHelper, scheduler);
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
        return new DbHelper(context);
    }

    @Provides
    @NonNull
    Scheduler provideScheduler() {
        return Schedulers.io();
    }
}
