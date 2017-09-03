package com.example.swapnilgupta.sharechat.sqlite;

import android.provider.BaseColumns;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

final class SQLContract {

    private SQLContract() {
    }


    static class FeedItem implements BaseColumns {

        static final String TABLE_NAME = "feed_items";
        static final String COLUMN_NAME_TYPE = "type";
        static final String COLUMN_NAME_AUTHOR_NAME = "author_name";
        static final String COLUMN_NAME_URL = "url";
        static final String COLUMN_NAME_POSTED_ON = "postenOn";
        static final String COLUMN_NAME_AUTHOR_CONTACT = "author_contact";
        static final String COLUMN_NAME_AUTHOR_DOB = "author_dob";
        static final String COLUMN_NAME_AUTHOR_STATUS = "author_status";
        static final String COLUMN_NAME_AUTHOR_GENDER = "author_gender";
        static final String COLUMN_NAME_PROFILE_URL = "author_url";

    }
}
