package com.shashank.platform.classroomappui.repository;

import retrofit2.Retrofit;

public class ProfessorRepository {

    public static Retrofit retrofit;
    private static final String GET_PROF_URL="http://localhost:8080/professorsList";
}
