package com.shashank.platform.classroomappui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.shashank.platform.classroomappui.businessLogic.DiscussionData;
import com.shashank.platform.classroomappui.businessLogic.DiscussionDataSet;
import com.shashank.platform.classroomappui.businessLogic.ProfileData;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class ClassRoomUIMain extends Application {

    private boolean isRealmSet;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeRealm();
    }

    private void initializeRealm() {
        // initialize Realm
        Realm.init(this);

        // Realm Configuration setup
        RealmConfiguration config = new RealmConfiguration.Builder().allowWritesOnUiThread(true).deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // find profile created or not
                DiscussionDataSet discussionDataSet = realm.where(DiscussionDataSet.class).findFirst();
                if(discussionDataSet!=null) {
                    isRealmSet = discussionDataSet.isProfileSetup();
                } else {
                    isRealmSet = false;
                }
            }
        });


        if (!isRealmSet) {
            // set the discussion forum values
            Realm realm = null;

            try {
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        try {
                            DiscussionData discussionData = new DiscussionData();
                            discussionData.setUserName("Dileep");
                            discussionData.setContent("Need to conduct a programming marathon. any ideas?");
                            discussionData.setTimeStamp("Yesterday 09:45 PM");

                            DiscussionData discussionData1 = new DiscussionData();
                            discussionData1.setUserName("Diana Prince");
                            discussionData1.setContent("Good idea. i will notify our hod about this. i will take care");
                            discussionData1.setTimeStamp("Yesterday 11:45 PM");


                            System.out.println("REALM VALUES SET For Discussion");

                            realm.copyToRealm(discussionData);
                            realm.copyToRealm(discussionData1);

                            DiscussionDataSet discussionDataSet = new DiscussionDataSet();
                            discussionDataSet.setProfileSetup(true);
                            realm.copyToRealm(discussionDataSet);

                        } catch (RealmPrimaryKeyConstraintException e) {
                            Toast.makeText(getApplicationContext(), "Primary Key exists, Press Update instead", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }
        }
    }
}
