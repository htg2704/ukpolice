package com.example.ukpolicedata;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CrimeDetailsWithLocation {
    @GET("crimes-at-location")
    Call<List<CrimeDetails>> getCrimeDetails(@Query("date") String str, @Query("lat") String str2, @Query("lng") String str3);
}