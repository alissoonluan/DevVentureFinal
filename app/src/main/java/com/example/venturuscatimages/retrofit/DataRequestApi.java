package com.example.venturuscatimages.retrofit;

import com.example.venturuscatimages.model.Cat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DataRequestApi {

    @Headers("Authorization: Client-ID 1ceddedc03a5d71")
    @GET("gallery/search")
    Call<Cat> getCatImages(@Query("q") String searchTag, @Query("q_type") String fileType);
}
