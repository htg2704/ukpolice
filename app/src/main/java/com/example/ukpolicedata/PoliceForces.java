package com.example.ukpolicedata;

import com.google.gson.annotations.SerializedName;

public class PoliceForces {
    @SerializedName("id")

    /* renamed from: id */
    private String f43id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return this.f43id;
    }

    public String getName() {
        return this.name;
    }
}