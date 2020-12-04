package com.example.philipshueapk;

import android.util.Log;

import com.example.philipshueapk.lamp.HueInfo;
import com.example.philipshueapk.lamp.LampProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;

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
    private String[] lampNames = new String[3];
    private ArrayList<LampsChangedListener> lampsChangedListeners = new ArrayList<>();



    /**
     * initializes this singleton and requests all the lights from the bridge
     */
    public void init() {
        initHttpClient();
        getAllLights();
    }

    /**
     * initializes the okhttp client
     */
    private void initHttpClient() {
        client = new OkHttpClient();
    }

    /**
     * returns the HueInfo instance that holds all lamp info of the current bridge
     * @return the HueInfo instance retrieved in the getAllLights method
     */
    public HueInfo getHueInfo() {
        return hueInfo;
    }

    /**
     * gets all lights from the bridge
     */
    public void getAllLights() {
        final String uri = bridgeUri + username + category;
        if (!canLog()) Log.d(TAG, "SENDING ALL LIGHTS REQUEST :" + uri);
        // kinda a workaround, create a final array with size 1, so we can store the received hue info outside the thread
        final HueInfo[] hueInfos = new HueInfo[1];
        Thread t = new Thread(() -> {

            HueInfo hueInfo;
            if (canLog()) Log.d(TAG, "Starting new thread");
            Request request = new Request.Builder().url(uri).build();

            try (Response response = client.newCall(request).execute()) {
                String responseString = response.body().string();
                if (canLog()) Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + responseString);
                hueInfos[0] = new ObjectMapper().readValue(responseString, HueInfo.class);
                notifyLampsChangedListeners();
            }catch (IOException e) {
                if (canLog()) Log.d(TAG,"Exception while handling response: " + e.getMessage());
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

    /**
     * sends a put request to the bridge, to change the state of a lamp
     * @param uri the uri to send to the api
     * @param requestBody the requestbody to send. This holds what needs to be changed
     */
    public void sendPutRequest(String uri, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(uri)
                .put(requestBody)
                .build();

        Thread t = new Thread(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (canLog()) Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + response.body().string());
                notifyLampsChangedListeners();
            }catch (IOException e) {
                if (canLog()) Log.d(TAG,"Exception while handling response: " + e.getMessage());
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void renameLamp(int id, String newName) {
        final String uri = bridgeUri + username + "/lights/" + id;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("name",newName);

        try {
            String jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
            RequestBody requestBody = RequestBody.create(jsonBody,JSON);
            if (canLog()) Log.d(TAG, "Sending rename request: " + jsonBody);
            lampNames[id-1] = newName;
            sendPutRequest(uri,requestBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the lamp with the specific id
     * @param id the id of the lamp to get
     * @return a LampProduct instance with all the info of the lamp
     */
    public LampProduct getLamp(int id) {
        final String uri = bridgeUri + username + "/lights/" + id;
        if (canLog()) Log.d(TAG, "SENDING GET LAMP REQUEST :" + uri);
        final LampProduct[] lampProducts = new LampProduct[1];
        Thread t = new Thread(() -> {

            Log.d(TAG, "Starting new thread");
            Request request = new Request.Builder().url(uri).build();

            try (Response response = client.newCall(request).execute()) {
                String responseString = response.body().string();
                if (canLog()) Log.d(TAG, "GOT RESPONSE FROM EMULATOR: " + responseString);
                lampProducts[0] = new ObjectMapper().readValue(responseString, LampProduct.class);
            }catch (IOException e) {
                if (canLog()) Log.d(TAG,"Exception while handling response: " + e.getMessage());
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

    /**
     * sets the state of the given lamp with the given json values using the {@link HttpHandler#sendPutRequest(String, RequestBody) sendPutRequest} method
     * @param id the id of the lamp to set
     * @param jsonBody the body of the request. This contains what values should be set
     */
    public void setLampState(int id, String jsonBody) {
        final String uri = bridgeUri + username + "/lights/" + id + "/state";
        RequestBody requestBody = RequestBody.create(jsonBody,JSON);
        if (canLog()) Log.d(TAG, "Sending state request for lamp " + id + "\nBody:\n" + jsonBody);

        sendPutRequest(uri,requestBody);
    }

    private void notifyLampsChangedListeners() {
        if (canLog()) Log.d(TAG,"Notifying all lamp changed listeners");
        for (LampsChangedListener lampChangedListener : this.lampsChangedListeners) {
            lampChangedListener.onLampsChanged(hueInfo.getLamps());
        }
    }

    public ArrayList<LampProduct> getLamps() {
        return hueInfo.getLamps();
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

    public String[] getLampNames() {
        return lampNames;
    }

    public void addLampsChangedListener(LampsChangedListener lampsChangedListener) {
        this.lampsChangedListeners.add(lampsChangedListener);
    }

    public void setBridgeUri(String bridgeUri) {
        this.bridgeUri = bridgeUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * method that checks if the method being called is in a test instance or not. This is used to see if we can use logs or not
     * @return true if the method is being called from a test, false otherwise
     */
    public static boolean canLog() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }

}
