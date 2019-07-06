package com.example.ukpolicedata;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class data_force_activity extends AppCompatActivity {
    String id,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_force);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        api Api = retrofit.create(api.class);



                Api.getModel(id,name).enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call <model> call, Response<model> response) {
            Log.i("id",response.body().toString());

            }

            @Override
            public void onFailure(Call<model>  call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
