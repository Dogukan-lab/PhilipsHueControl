package com.example.philipshueapk;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.philipshueapk.lamp.HueInfo;
import com.example.philipshueapk.lamp.LampProduct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.jupiter.api.Assertions.*;

class MockTest {
    final MockWebServer mockWebServer = new MockWebServer();
    final String requestBody = "{\n" +
            "  \"1\": {\n" +
            "    \"modelid\": \"LCT001\",\n" +
            "    \"name\": \"Hue Lamp 1\",\n" +
            "    \"swversion\": \"65003148\",\n" +
            "    \"state\": {\n" +
            "      \"xy\": [\n" +
            "        0,\n" +
            "        0\n" +
            "      ],\n" +
            "      \"ct\": 0,\n" +
            "      \"alert\": \"none\",\n" +
            "      \"sat\": 241,\n" +
            "      \"effect\": \"none\",\n" +
            "      \"bri\": 254,\n" +
            "      \"hue\": 499,\n" +
            "      \"colormode\": \"hs\",\n" +
            "      \"reachable\": true,\n" +
            "      \"on\": true\n" +
            "    },\n" +
            "    \"type\": \"Extended color light\",\n" +
            "    \"pointsymbol\": {\n" +
            "      \"1\": \"none\",\n" +
            "      \"2\": \"none\",\n" +
            "      \"3\": \"none\",\n" +
            "      \"4\": \"none\",\n" +
            "      \"5\": \"none\",\n" +
            "      \"6\": \"none\",\n" +
            "      \"7\": \"none\",\n" +
            "      \"8\": \"none\"\n" +
            "    },\n" +
            "    \"uniqueid\": \"00:17:88:01:00:d4:12:08-0a\"\n" +
            "  },\n" +
            "  \"2\": {\n" +
            "    \"modelid\": \"LCT001\",\n" +
            "    \"name\": \"Hue Lamp 2\",\n" +
            "    \"swversion\": \"65003148\",\n" +
            "    \"state\": {\n" +
            "      \"xy\": [\n" +
            "        0.346,\n" +
            "        0.3568\n" +
            "      ],\n" +
            "      \"ct\": 201,\n" +
            "      \"alert\": \"none\",\n" +
            "      \"sat\": 239,\n" +
            "      \"effect\": \"none\",\n" +
            "      \"bri\": 254,\n" +
            "      \"hue\": 33864,\n" +
            "      \"colormode\": \"hs\",\n" +
            "      \"reachable\": true,\n" +
            "      \"on\": true\n" +
            "    },\n" +
            "    \"type\": \"Extended color light\",\n" +
            "    \"pointsymbol\": {\n" +
            "      \"1\": \"none\",\n" +
            "      \"2\": \"none\",\n" +
            "      \"3\": \"none\",\n" +
            "      \"4\": \"none\",\n" +
            "      \"5\": \"none\",\n" +
            "      \"6\": \"none\",\n" +
            "      \"7\": \"none\",\n" +
            "      \"8\": \"none\"\n" +
            "    },\n" +
            "    \"uniqueid\": \"00:17:88:01:00:d4:12:08-0b\"\n" +
            "  },\n" +
            "  \"3\": {\n" +
            "    \"modelid\": \"LCT001\",\n" +
            "    \"name\": \"Hue Lamp 3\",\n" +
            "    \"swversion\": \"65003148\",\n" +
            "    \"state\": {\n" +
            "      \"xy\": [\n" +
            "        0.346,\n" +
            "        0.3568\n" +
            "      ],\n" +
            "      \"ct\": 201,\n" +
            "      \"alert\": \"none\",\n" +
            "      \"sat\": 220,\n" +
            "      \"effect\": \"none\",\n" +
            "      \"bri\": 254,\n" +
            "      \"hue\": 18072,\n" +
            "      \"colormode\": \"hs\",\n" +
            "      \"reachable\": true,\n" +
            "      \"on\": true\n" +
            "    },\n" +
            "    \"type\": \"Extended color light\",\n" +
            "    \"pointsymbol\": {\n" +
            "      \"1\": \"none\",\n" +
            "      \"2\": \"none\",\n" +
            "      \"3\": \"none\",\n" +
            "      \"4\": \"none\",\n" +
            "      \"5\": \"none\",\n" +
            "      \"6\": \"none\",\n" +
            "      \"7\": \"none\",\n" +
            "      \"8\": \"none\"\n" +
            "    },\n" +
            "    \"uniqueid\": \"00:17:88:01:00:d4:12:08-0c\"\n" +
            "  }\n" +
            "}";

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(requestBody));
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testResponseFromServer() {

        HttpUrl baseUrl = mockWebServer.url("/api/newdeveloper/lights");
        HttpHandler httpHandler = HttpHandler.INSTANCE;
        httpHandler.setBridgeUri(baseUrl.toString());
        httpHandler.init();

        assertNotNull(httpHandler.getHueInfo());
        assertNotNull(httpHandler.getLamps());

    }

    @DisplayName("integration test to see if the lamp module is working correctly with the httphandler")
    @Test
    void testIntegrationLampsInHttpHandler() {
        // get the response from the server
        HttpUrl baseUrl = mockWebServer.url("/api/newdeveloper/lights");
        HttpHandler httpHandler = HttpHandler.INSTANCE;
        httpHandler.setBridgeUri(baseUrl.toString());
        httpHandler.init();

        HueInfo hueInfo = httpHandler.getHueInfo();
        // check if the hueinfo and products are not null
        assertNotNull(hueInfo);
        assertNotNull(hueInfo.getProduct());
        assertNotNull(httpHandler.getLamps());

        // check if there are 3 lamps
        assertEquals(3, httpHandler.getLamps().size());

        // check if the names are correct. If the names are correct we can say that the integration test is finished, because the HueInfo class is 100% unit tested
        assertEquals("Hue Lamp 1",hueInfo.getProduct().getName());
        assertEquals("Hue Lamp 2",hueInfo.getProduct2().getName());
        assertEquals("Hue Lamp 3",hueInfo.getProduct3().getName());

    }

    @Test
    void testLampNames() {
        // get the response from the server
        HttpUrl baseUrl = mockWebServer.url("/api/newdeveloper/lights");
        HttpHandler httpHandler = HttpHandler.INSTANCE;
        httpHandler.setBridgeUri(baseUrl.toString());
        httpHandler.init();

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("[{\"success\":{\"/lights/1/name\":\"Bedroom Light\"}}]"));
        httpHandler.renameLamp(1,"Bedroom Light");

        assertNotNull(httpHandler.getLampNames());
        assertEquals("Bedroom Light",httpHandler.getLampNames()[0]);
    }
}