package com.example.philipshueapk;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public enum HttpHandler {
    INSTANCE;

    private OkHttpClient client;
    private String username = "newDeveloper";

    public void initHttpClient() {
        client = new OkHttpClient();
    }

    /**
     * sends a request to the API and returns the response
     * @param url the url to send to the api
     * @return a String with the response
     * @throws Exception if there was an error with the response
     */
    public String createRequest(String url) throws Exception{
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()){
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public OkHttpClient getClient() {
        return client;
    }

    public String getUsername() {
        return username;
    }
}
