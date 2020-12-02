package com.example.philipshueapk;

import android.util.Log;

import com.example.philipshueapk.lamp.HueInfo;
import com.example.philipshueapk.lamp.LampProduct;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private HueInfo hueInfo;

    public void init() {
        initHttpClient();
        getAllLights();
    }

    public void initHttpClient() {
        client = new OkHttpClient();
    }

    public HueInfo getHueInfo() {
        return hueInfo;
    }

    private void getAllLights() {
        final String uri = bridgeUri + username + category;
        Log.d(TAG, "SENDING ALL LIGHTS REQUEST :" + uri);
        // kinda a workaround, create a final array with size 1, so we can store the received hue info outside the thread
        final HueInfo[] hueInfos = new HueInfo[1];
        Thread t = new Thread(() -> {

            HueInfo hueInfo;
            Log.d(TAG, "Starting new thread");
            Request request = new Request.Builder().url(uri).build();

            try (Response response = client.newCall(request).execute()) {
                String responseString = response.body().string();
                Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + responseString);
                hueInfos[0] = new ObjectMapper().readValue(responseString, HueInfo.class);
            }catch (IOException e) {
                Log.d(TAG,"Exception while handling response: " + e.getMessage());
                hueInfos[0] = new HueInfo();
            }

        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.hueInfo = hueInfos[0];

    }

    private void sendRequest(String uri, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(uri)
                .put(requestBody)
                .build();

        Thread t = new Thread(() -> {
            try (Response response = client.newCall(request).execute()) {
                Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + response.body().string());
            }catch (IOException e) {
                Log.d(TAG,"Exception while handling response: " + e.getMessage());
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LampProduct getLamp(int id) {
        final String uri = bridgeUri + username + "/lights/" + id;
        Log.d(TAG, "SENDING GET LAMP REQUEST :" + uri);
        final LampProduct[] lampProducts = new LampProduct[1];
        Thread t = new Thread(() -> {

            Log.d(TAG, "Starting new thread");
            Request request = new Request.Builder().url(uri).build();

            try (Response response = client.newCall(request).execute()) {
                String responseString = response.body().string();
                Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + responseString);
                lampProducts[0] = new ObjectMapper().readValue(responseString, LampProduct.class);
            }catch (IOException e) {
                Log.d(TAG,"Exception while handling response: " + e.getMessage());
                lampProducts[0] = new LampProduct();
            }

        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return lampProducts[0];
    }

    public void setLampState(int id, String jsonBody) {
        final String uri = bridgeUri + username + "/lights/" + id + "/state";
        RequestBody requestBody = RequestBody.create(jsonBody,JSON);
        Log.d(TAG, "Sending state request for lamp " + id + "\nBody:\n" + jsonBody);

        sendRequest(uri,requestBody);
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
