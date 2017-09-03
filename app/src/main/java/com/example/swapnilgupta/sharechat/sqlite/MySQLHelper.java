package com.example.swapnilgupta.sharechat.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.swapnilgupta.sharechat.MyApplication;
import com.example.swapnilgupta.sharechat.models.FeedItem;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class MySQLHelper extends SQLiteOpenHelper {

    private static final String TAG = "MySQLHelper";

    // If you change the database schema, you must increment the database version.
    private static final int DB_VERSION = 1;

    // DB Name, same is used to name the sqlite DB file
    private static final String DB_NAME = "app.db";

    public MySQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db = MyApplication.mSQLHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            Log.d(TAG, "Trying to create the course tables");
            db.execSQL(SQL_CREATE_FEED_ITEMS);
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        Log.d(TAG, "Creating of tables finished");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Write here migration scripts for database updates ..
    }

    private static final String SQL_CREATE_FEED_ITEMS =
            "CREATE TABLE " + SQLContract.FeedItem.TABLE_NAME + "("
                    + SQLContract.FeedItem._ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + SQLContract.FeedItem.COLUMN_NAME_TYPE + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_AUTHOR_NAME + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_URL + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_POSTED_ON + " INTEGER,"
                    + SQLContract.FeedItem.COLUMN_NAME_AUTHOR_CONTACT + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_AUTHOR_DOB + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_AUTHOR_STATUS + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_AUTHOR_GENDER + " TEXT,"
                    + SQLContract.FeedItem.COLUMN_NAME_PROFILE_URL + " TEXT)";
}
