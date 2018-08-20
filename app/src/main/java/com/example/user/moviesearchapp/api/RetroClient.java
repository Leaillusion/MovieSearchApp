package com.example.user.moviesearchapp.api;


import com.example.user.moviesearchapp.api.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL = "http://www.omdbapi.com/"; //URLS

    //Get API Service
    public static ApiService getApiService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        //Get Retrofit
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiService.class);
    }

}
