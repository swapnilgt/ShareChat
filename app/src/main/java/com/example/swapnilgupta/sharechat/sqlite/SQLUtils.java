package com.example.swapnilgupta.sharechat.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.swapnilgupta.sharechat.MyApplication;
import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class SQLUtils {

    private static final String TAG = "SQLUtils";

    public static boolean insertNewFeeds(List<FeedItem> items) {

        // getting the db handle for writing the data ..
        final SQLiteDatabase db = MyApplication.mSQLHelper.getWritableDatabase();

        db.beginTransaction();

        try {
            for(FeedItem f: items) {
                final ContentValues cv = new ContentValues();

                cv.put(SQLContract.FeedItem._ID, f.getId());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_AUTHOR_NAME, f.getAuthor_name());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_AUTHOR_CONTACT, f.getAuthor_contact());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_AUTHOR_DOB, f.getAuthor_dob());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_AUTHOR_GENDER, f.getAuthor_gender());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_AUTHOR_STATUS, f.getAuthor_status());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_POSTED_ON, f.getPostedOn());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_TYPE, f.getType());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_URL, f.getUrl());
                cv.put(SQLContract.FeedItem.COLUMN_NAME_PROFILE_URL, f.getUrl());

                db.insertOrThrow(SQLContract.FeedItem.TABLE_NAME, null, cv);
            }
        } catch (SQLException e) {
            Log.d(TAG, "Exception in inserting: " + e.getMessage());
            return false;
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }

        return true;
    }

    public static boolean flushLocalFeeds() {
        // getting the db handle for writing the data ..
        final SQLiteDatabase db = MyApplication.mSQLHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("delete from " + SQLContract.FeedItem.TABLE_NAME);
        } catch (SQLException e) {
            Log.d(TAG, "Exception in inserting: " + e.getMessage());
            return false;
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        Log.d(TAG, "Successfully flushed the local storage ..");
        return true;
    }

    public static List<FeedItem> fetchFeedItems() {
        // getting the handle of the database object ..
        final SQLiteDatabase db = MyApplication.mSQLHelper.getReadableDatabase();

        final Cursor c = db.query(
                SQLContract.FeedItem.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(c != null) {
            Log.d(TAG, "The number of medias are: " + c.getCount());
            List<FeedItem> retList = new ArrayList<>();

            final int COLUMN_ID = c.getColumnIndex(SQLContract.FeedItem._ID);
            final int COLUMN_NAME_AUTHOR_NAME =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_AUTHOR_NAME);
            final int COLUMN_NAME_TYPE =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_TYPE);
            final int COLUMN_NAME_URL =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_URL);
            final int COLUMN_NAME_POSTED_ON =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_POSTED_ON);
            final int COLUMN_NAME_AUTHOR_CONTACT =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_AUTHOR_CONTACT);
            final int COLUMN_NAME_AUTHOR_DOB =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_AUTHOR_DOB);
            final int COLUMN_NAME_AUTHOR_STATUS =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_AUTHOR_STATUS);
            final int COLUMN_NAME_AUTHOR_GENDER =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_AUTHOR_GENDER);
            final int COLUMN_NAME_PROFILE_URL =c.getColumnIndex
                    (SQLContract.FeedItem.COLUMN_NAME_PROFILE_URL);

            while(c.moveToNext()) {
                final FeedItem i = new FeedItem();
                i.setId(c.getInt(COLUMN_ID));
                i.setAuthor_name(c.getString(COLUMN_NAME_AUTHOR_NAME));
                i.setType(c.getString(COLUMN_NAME_TYPE));
                i.setUrl(c.getString(COLUMN_NAME_URL));
                i.setPostedOn(c.getLong(COLUMN_NAME_POSTED_ON));
                i.setAuthor_contact(c.getString(COLUMN_NAME_AUTHOR_CONTACT));
                i.setAuthor_dob(c.getString(COLUMN_NAME_AUTHOR_DOB));
                i.setAuthor_status(c.getString(COLUMN_NAME_AUTHOR_STATUS));
                i.setAuthor_gender(c.getString(COLUMN_NAME_AUTHOR_GENDER));
                i.setProfile_url(c.getString(COLUMN_NAME_PROFILE_URL));

                retList.add(i);
            }
            c.close();
            Log.d(TAG, "Number of items fetched from local storage is: " + retList.size());
            return retList;

        } else {
            Log.d(TAG, "The cursor is null.");
            return null;
        }
    }
}
