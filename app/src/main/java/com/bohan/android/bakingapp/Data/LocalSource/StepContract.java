package com.bohan.android.bakingapp.Data.LocalSource;

/**
 * Created by Bo Han.
 */

import android.provider.BaseColumns;

public class StepContract {

    public static abstract class StepEntry implements BaseColumns {

        public static final String TABLE_NAME = "steps";
        public static final String COLUMN_RECIPE_ID = "recipeid";
        public static final String COLUMN_STEP_ID = "stepId";
        public static final String COLUMN_SHORT_DESC = "shortDesc";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_VIDEO_URL = "video";
        public static final String COLUMN_THUMB_URL = "thumb";
    }

    static final String SQL_QUERY_CREATE =
            "CREATE TABLE " + StepEntry.TABLE_NAME + " ("
                    + StepEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + StepEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL,"
                    + StepEntry.COLUMN_STEP_ID + " INTEGER NOT NULL,"
                    + StepEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL,"
                    + StepEntry.COLUMN_DESC + " TEXT NOT NULL,"
                    + StepEntry.COLUMN_VIDEO_URL + " TEXT NOT NULL,"
                    + StepEntry.COLUMN_THUMB_URL + " TEXT NOT NULL"
                    + ")";
}
