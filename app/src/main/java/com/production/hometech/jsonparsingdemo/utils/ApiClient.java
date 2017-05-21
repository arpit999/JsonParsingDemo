package com.production.hometech.jsonparsingdemo.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arpit on 17-May-17.
 */

public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String BASE_URL_ANDROIDHIVE = "http://api.androidhive.info/json/";
    public static final String BASE_URL_PRATIKBUTANI= "http://pratikbutani.x10.mx/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url) {
        if (retrofit == null) {
            retrofit= new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
