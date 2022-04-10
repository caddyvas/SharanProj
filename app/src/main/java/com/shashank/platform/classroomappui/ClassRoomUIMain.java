package com.shashank.platform.classroomappui;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ClassRoomUIMain extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeRealm();
    }

    private void initializeRealm() {
        // initialize Realm
        Realm.init(getApplicationContext());

        // Realm Configuration setup
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);

    }
}
