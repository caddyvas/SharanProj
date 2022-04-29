package com.shashank.platform.classroomappui.mainUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.model.ProfessorList;
import com.shashank.platform.classroomappui.model.Professors;
import com.shashank.platform.classroomappui.repository.CallMethods;
import com.shashank.platform.classroomappui.repository.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lectures extends AppCompatActivity {

    private List<Professors> professorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CallMethods callMethods = RetrofitClient.getRetrofitInstance().create(CallMethods.class);
        Call<List<Professors>> call = callMethods.getProfessorsList();



        call.enqueue(new Callback<List<Professors>>() {
            @Override
            public void onResponse(Call<List<Professors>> call, Response<List<Professors>> response) {

                System.out.println("GETPROFESSORSLIST - SUCCESS CODE: " +response.code());
                if (response.body() != null) {
                    professorsList = response.body();
                    for(Professors professors : professorsList) {
                        System.out.println("CATCH - NAME: " +professors.getName() +
                                " - SUBJECT: " +professors.getSubject());
                    }
                } else {
                    System.out.println("CATCH - RESPONSE NULL");
                }
            }

            @Override
            public void onFailure(Call<List<Professors>> call, Throwable throwable) {

                System.out.println("CATCH - FAILURE: " +throwable.getMessage());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
