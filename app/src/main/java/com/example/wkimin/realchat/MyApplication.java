package com.example.wkimin.realchat;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by wkimin on 2017-06-21.
 *
 */

public class MyApplication extends Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
    }
}
