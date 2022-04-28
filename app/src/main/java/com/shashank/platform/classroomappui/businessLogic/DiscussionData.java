package com.shashank.platform.classroomappui.businessLogic;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class DiscussionData extends RealmObject {

    @Required
    String userName = "";

    @Required
    String content = "";

    @Required
    String timeStamp = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
