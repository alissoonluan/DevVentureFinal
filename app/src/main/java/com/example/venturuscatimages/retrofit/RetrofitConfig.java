package com.example.venturuscatimages.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static final String baseUrl = "https://api.imgur.com/3/";

    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public DataRequestApi getFetchPhotosAPI() {
        return retrofit.create(DataRequestApi.class);
    }
}
