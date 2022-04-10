package com.shashank.platform.classroomappui.mainUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.businessLogic.ProfileData;

import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class ProfileCreation extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText aboutMe;
    EditText phoneNo;
    EditText gpa;
    EditText email;
    EditText area;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        name = findViewById(R.id.enter_name_EditText);
        aboutMe = findViewById(R.id.aboutMe_editText);
        phoneNo = findViewById(R.id.phoneNo_editText);
        gpa = findViewById(R.id.gpa_editText);
        email = findViewById(R.id.email_editText);
        area = findViewById(R.id.areaOfInterest_editText);
        submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        Realm realm = null;
        if (view.getId() == R.id.submit_button) {
            if(!areAllFieldsSet()) {
                Toast.makeText(getApplicationContext(), "Please complete all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        try {
                            ProfileData profileInfo = new ProfileData();
                            profileInfo.setName(name.getText().toString().trim());
                            profileInfo.setAboutMe(aboutMe.getText().toString());
                            profileInfo.setEmail(email.getText().toString().trim());
                            profileInfo.setAreasOfInterest(area.getText().toString());
                            profileInfo.setGpa(gpa.getText().toString().trim());
                            profileInfo.setPhoneNo(phoneNo.getText().toString().trim());
                            profileInfo.setProfileSetup(true);

                            System.out.println("REALM VALUES SET");

                            realm.copyToRealm(profileInfo);

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

            finish();
        }
    }

    private boolean areAllFieldsSet() {
        String strName = name.getText().toString();
        String strAboutMe = aboutMe.getText().toString();
        String strPhoneNo = phoneNo.getText().toString();
        String strGpa = gpa.getText().toString();
        String strEmail = email.getText().toString();
        String strArea = area.getText().toString();

        return !strName.matches("") && !strAboutMe.matches("") && !strPhoneNo.matches("") && !strGpa.matches("")
                && !strEmail.matches("") && !strArea.matches("");
    }
}
