package com.shashank.platform.classroomappui.mainUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.platform.classroomappui.R;

public class Announcements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}