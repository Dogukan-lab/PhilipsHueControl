package com.example.philipshueapk.lamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class LampCapabilitiesTest {
    private LampCapabilities lampCapabilities;

    @BeforeEach
    public void setUp() {
        this.lampCapabilities = new LampCapabilities();
    }

    @Test
    public void getCertified() {
        lampCapabilities.setCertified(true);
        assertTrue(lampCapabilities.getCertified());
        lampCapabilities.setCertified(false);
        assertFalse(lampCapabilities.getCertified());
    }

    @Test
    public void getControl() {
        // todo test
    }

    @Test
    public void getStreaming() {

        LampStreaming lampStreaming = new LampStreaming();
        lampStreaming.setProxy(true);
        lampStreaming.setRenderer(false);
        lampStreaming.setAdditionalProperty("PropertyTest",69);

        lampCapabilities.setStreaming(lampStreaming);
        assertNotNull(lampCapabilities.getStreaming());
        LampStreaming newStreaming = lampCapabilities.getStreaming();
        assertTrue(newStreaming.getProxy());
        assertFalse(newStreaming.getRenderer());
        assertEquals(69,newStreaming.getAdditionalProperties().get("PropertyTest"));

        // todo test
    }

    @Test
    public void getAdditionalProperties() {
        lampCapabilities.setAdditionalProperty("Test", 291);
        assertEquals(291, lampCapabilities.getAdditionalProperties().get("Test"));
    }
}