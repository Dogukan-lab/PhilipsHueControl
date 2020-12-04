package com.example.philipshueapk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.jupiter.api.Assertions.*;

class MockTest {
    final MockWebServer mockWebServer = new MockWebServer();
    final String requestBody = "{\n" +
            "\"1\": {\n" +
            "        \"state\": {\n" +
            "            \"on\": false,\n" +
            "            \"bri\": 1,\n" +
            "            \"hue\": 33761,\n" +
            "            \"sat\": 254,\n" +
            "            \"effect\": \"none\",\n" +
            "            \"xy\": [\n" +
            "                0.3171,\n" +
            "                0.3366\n" +
            "            ],\n" +
            "            \"ct\": 159,\n" +
            "            \"alert\": \"none\",\n" +
            "            \"colormode\": \"xy\",\n" +
            "            \"mode\": \"homeautomation\",\n" +
            "            \"reachable\": true\n" +
            "        },\n" +
            "        \"swupdate\": {\n" +
            "            \"state\": \"noupdates\",\n" +
            "            \"lastinstall\": \"2018-01-02T19:24:20\"\n" +
            "        },\n" +
            "        \"type\": \"Extended color light\",\n" +
            "        \"name\": \"Hue color lamp 7\",\n" +
            "        \"modelid\": \"LCT007\",\n" +
            "        \"manufacturername\": \"Philips\",\n" +
            "        \"productname\": \"Hue color lamp\",\n" +
            "        \"capabilities\": {\n" +
            "            \"certified\": true,\n" +
            "            \"control\": {\n" +
            "                \"mindimlevel\": 5000,\n" +
            "                \"maxlumen\": 600,\n" +
            "                \"colorgamuttype\": \"B\",\n" +
            "                \"colorgamut\": [\n" +
            "                    [\n" +
            "                        0.675,\n" +
            "                        0.322\n" +
            "                    ],\n" +
            "                    [\n" +
            "                        0.409,\n" +
            "                        0.518\n" +
            "                    ],\n" +
            "                    [\n" +
            "                        0.167,\n" +
            "                        0.04\n" +
            "                    ]\n" +
            "                ],\n" +
            "                \"ct\": {\n" +
            "                    \"min\": 153,\n" +
            "                    \"max\": 500\n" +
            "                }\n" +
            "            },\n" +
            "            \"streaming\": {\n" +
            "                \"renderer\": true,\n" +
            "                \"proxy\": false\n" +
            "            }\n" +
            "        },\n" +
            "        \"config\": {\n" +
            "            \"archetype\": \"sultanbulb\",\n" +
            "            \"function\": \"mixed\",\n" +
            "            \"direction\": \"omnidirectional\"\n" +
            "        },\n" +
            "        \"uniqueid\": \"00:17:88:01:00:bd:c7:b9-0b\",\n" +
            "        \"swversion\": \"5.105.0.21169\"\n" +
            "    }\n" +
            "}";

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testResponseFromServer() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(requestBody));

        HttpUrl baseUrl = mockWebServer.url("/api/newdeveloper/lights");
        HttpHandler httpHandler = HttpHandler.INSTANCE;
        httpHandler.setBridgeUri(baseUrl.toString());
        httpHandler.init();

        assertNotNull(httpHandler.getHueInfo());
        assertNotNull(httpHandler.getLamps());

    }
}