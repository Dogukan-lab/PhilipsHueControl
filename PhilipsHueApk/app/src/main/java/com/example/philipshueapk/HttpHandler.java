package com.example.philipshueapk;

import okhttp3.OkHttpClient;

public enum HttpHandler {
    INSTANCE;

    private OkHttpClient client;
    private String username = "newDeveloper";

    public void initHttpClient() {
        client = new OkHttpClient();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public String getUsername() {
        return username;
    }
}
