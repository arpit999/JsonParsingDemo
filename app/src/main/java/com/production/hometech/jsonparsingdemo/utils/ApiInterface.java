package com.production.hometech.jsonparsingdemo.utils;

import com.production.hometech.jsonparsingdemo.model.AndroidHive;
import com.production.hometech.jsonparsingdemo.model.Movie;
import com.production.hometech.jsonparsingdemo.model.Pratik;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Arpit on 17-May-17.
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movies.json")
    Call<List<AndroidHive>> getAllMovies();

    @GET("json_data.json")
    Call<Pratik> getAllContacts();

}
