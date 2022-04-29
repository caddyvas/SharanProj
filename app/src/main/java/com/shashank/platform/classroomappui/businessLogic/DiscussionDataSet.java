package com.shashank.platform.classroomappui.businessLogic;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class DiscussionDataSet extends RealmObject {

    boolean isProfileSetup = false;

    public boolean isProfileSetup() {
        return isProfileSetup;
    }

    public void setProfileSetup(boolean profileSetup) {
        isProfileSetup = profileSetup;
    }
}
