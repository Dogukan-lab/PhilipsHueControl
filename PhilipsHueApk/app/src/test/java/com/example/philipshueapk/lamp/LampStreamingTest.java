package com.example.philipshueapk.lamp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LampStreamingTest {

    private LampStreaming streaming;

    @Before
    public void setup() {
        streaming = new LampStreaming();
    }

    @Test
    public void testRenderer() {
        streaming = new LampStreaming();
        streaming.setRenderer(true);
        assertTrue(streaming.getRenderer());
    }

    @Test
    public void testProxy() {
        streaming = new LampStreaming();
        streaming.setProxy(true);
        assertTrue(streaming.getProxy());
    }


    @Test
    public void testAdditionalProperties() {
        streaming = new LampStreaming();
        streaming.setAdditionalProperty("Test", 19);
        assertEquals(19, streaming.getAdditionalProperties().get("Test"));
    }

}