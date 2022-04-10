package com.shashank.platform.classroomappui.businessLogic;

import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class ProfileData extends RealmObject {

    @Required
    String name = "";

    @Required
    String phoneNo = "";

    @Required
    String email = "";

    @Required
    String gpa = "";

    @Required
    String areasOfInterest = "";

    @Required
    String aboutMe = "";

    boolean isProfileSetup = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(String areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean isProfileSetup() {
        return isProfileSetup;
    }

    public void setProfileSetup(boolean profileSetup) {
        isProfileSetup = profileSetup;
    }

}
