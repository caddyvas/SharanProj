package com.shashank.platform.classroomappui.mainUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.businessLogic.DiscussionData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class Discussion extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private RecyclerView mRecyclerView;
    private ArrayList<DiscussionData> discussionModelArrayList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ChatAdapter chatAdapter;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_recycler);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.discussionRecycler);
        mEditText = findViewById(R.id.discussion_comment_editTxt);
        Button button = findViewById(R.id.discussion_submit_btn);
        button.setOnClickListener(this);

        // pull data from server, stub for now
        mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // find profile created or not
                RealmResults<DiscussionData> discussionData = realm.where(DiscussionData.class).findAll();
                discussionModelArrayList.addAll(discussionData);
            }
        });

        // initializing adapter class and passing the data to the layout that the adapter has
        chatAdapter = new ChatAdapter(this, discussionModelArrayList);
        mRecyclerView.setHasFixedSize(true);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        linearLayoutManager = new LinearLayoutManager(this);

        // setting layoutmanager and adapter to our recycler view
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(chatAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.discussion_submit_btn) {
            String content = mEditText.getText().toString();
            if (!content.equals("")) {

                final DiscussionData discussionData = new DiscussionData();
                discussionData.setContent(content);
                discussionData.setUserName("Sharan");

                /* get current time */
                DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
                String dateString = dateFormat.format(new Date()).toString();
                System.out.println("Current time in AM/PM: " + dateString);

                discussionData.setTimeStamp(dateString);

                // add it to arraylist
                discussionModelArrayList.add(discussionData);

                // insertOrUpdate to realm
                mRealm = Realm.getDefaultInstance();

                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        // find profile created or not
                        realm.insertOrUpdate(discussionData);
                    }
                });

                chatAdapter.notifyDataSetChanged();
            }
        }
    }
}
