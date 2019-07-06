package com.example.ukpolicedata;

public class model {
    private String id;
    private String name;

    public model(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
