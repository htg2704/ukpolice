package com.example.ukpolicedata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api {
    String BASE_URL = "https://data.police.uk/docs/method/";

    @GET("forces")
    Call<model> getModel(@Query("id") String id,
                         @Query("name") String name);
}
