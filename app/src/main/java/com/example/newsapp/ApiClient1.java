package com.example.newsapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient1 {
    private static final String URL = "https://newsapi.org/v2/";
    private static ApiClient1 apiClient;
    private static Retrofit retrofit;

    private ApiClient1(){
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient1 getInstance(){
        if(apiClient==null){
            apiClient = new ApiClient1();
        }
        return apiClient;
    }

    public GetInfo getInfo(){
        return retrofit.create(GetInfo.class);
    }
}
