package com.example.swapnilgupta.sharechat;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.swapnilgupta.sharechat.sqlite.MySQLHelper;
import com.example.swapnilgupta.sharechat.sqlite.SQLUtils;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class MyApplication extends Application {

    public static MySQLHelper mSQLHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        // instantiating db helper ..
        mSQLHelper = new MySQLHelper(getApplicationContext());

    }
}
