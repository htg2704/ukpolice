package com.example.ukpolicedata;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ForcesInterface {
    @GET("forces")
    Call<List<PoliceForces>> getPoliceForces();
}
