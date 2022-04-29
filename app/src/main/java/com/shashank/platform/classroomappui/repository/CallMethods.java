package com.shashank.platform.classroomappui.repository;

import com.shashank.platform.classroomappui.model.ProfessorList;
import com.shashank.platform.classroomappui.model.Professors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallMethods {

    @GET("/professorsList")
    Call<List<Professors>> getProfessorsList();
}
