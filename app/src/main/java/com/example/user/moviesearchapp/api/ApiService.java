package com.example.user.moviesearchapp.api;

import com.example.user.moviesearchapp.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /*
    Retrifit get annotation with our URL
    And our method that will return us the List of MovieList
     */
    @GET("?s=batman&apikey=3ad23798")
    Call<MovieList> getMyJSON();  //метод для получения списков всех контактов
}
