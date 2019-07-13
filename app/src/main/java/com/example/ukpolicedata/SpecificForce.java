package com.example.ukpolicedata;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SpecificForce {
    @SerializedName("description")
    private String description;
    @SerializedName("engagement_methods")
    private List<EngagementMethods> engagementMethods;
    @SerializedName("id")

    /* renamed from: id */
    private String f45id;
    @SerializedName("name")
    private String name;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("url")
    private String url;

    public class EngagementMethods {
        @SerializedName("description")
        public String description;
        @SerializedName("title")
        public String title;
        @SerializedName("url")
        public String url;

        public EngagementMethods() {
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getUrl() {
        return this.url;
    }

    public List<EngagementMethods> getEngagementMethods() {
        return this.engagementMethods;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getId() {
        return this.f45id;
    }

    public String getName() {
        return this.name;
    }
}
