package com.example.philipshueapk;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public enum HttpHandler {
    INSTANCE;

    private static final String TAG = HttpHandler.class.getSimpleName();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client;
    private String bridgeUri = "http://10.0.2.2:8000/api/";
    private String username = "newdeveloper";
    private String category = "/lights";

    public void initHttpClient() {
        client = new OkHttpClient();
    }

    public void getAllLights() {
        final String uri = bridgeUri + username + category;
        Log.d(TAG, "SENDING ALL LIGHTS REQUEST :" + uri);
        new Thread(() -> {
            Log.d(TAG, "Starting new thread");
            Request request = new Request.Builder().url(uri).build();

            try (Response response = client.newCall(request).execute()) {
                Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + response.body().string());
            }catch (IOException e) {
                Log.d(TAG,"Exception while handling response: " + e.getMessage());
            }
        }).start();

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

    /**
     * sends a post request to the API and returns the response
     * @param url the url of the API to use
     * @param jsonMsg the json message to post
     * @return the response of the API
     * @throws IOException when there was an error with parsing the json or with the response
     */
    public String postRequest(String url, String jsonMsg) throws IOException {
        RequestBody body = RequestBody.create(jsonMsg, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public OkHttpClient getClient() {
        return client;
    }

    public String getUsername() {
        return username;
    }

    public String getBridgeUri() {
        return bridgeUri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
