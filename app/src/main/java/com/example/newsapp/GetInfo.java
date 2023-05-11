package com.example.newsapp;

import com.example.newsapp.Model.Headline;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetInfo {

    @GET("top-headlines")
    Call<Headline> getHeadline(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}
