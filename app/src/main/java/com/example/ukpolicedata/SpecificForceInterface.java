package com.example.ukpolicedata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpecificForceInterface {
    @GET("api/forces/{forceName}")
    Call<SpecificForce> getSpecificForce(@Path("forceName") String str);
}
