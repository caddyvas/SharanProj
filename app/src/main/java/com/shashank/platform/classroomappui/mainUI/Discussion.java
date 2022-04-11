package com.shashank.platform.classroomappui.mainUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shashank.platform.classroomappui.R;

public class Discussion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
