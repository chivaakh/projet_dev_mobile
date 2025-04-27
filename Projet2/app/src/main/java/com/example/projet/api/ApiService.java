package com.example.projet.api;

import com.example.projet.model.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/patients")
    Call<List<Patient>> getPatients();

    @POST("/api/patients")
    Call<Patient> createPatient(@Body Patient patient);

    @PUT("/api/patients/{id}")
    Call<Patient> updatePatient(@Path("id") Long id, @Body Patient patient);

    @DELETE("/api/patients/{id}")
    Call<Void> deletePatient(@Path("id") Long id);
}
