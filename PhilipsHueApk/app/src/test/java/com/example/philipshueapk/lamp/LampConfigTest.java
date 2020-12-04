package com.example.philipshueapk.lamp;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class LampConfigTest extends TestCase {

    private LampConfig lampConfig;

    @BeforeEach
    public void setUp() throws Exception {
        lampConfig = new LampConfig();
    }

    @AfterEach
    public void tearDown() throws Exception {
        lampConfig = null;
    }

    @Test
    public void testGetArchetype() {
        lampConfig.setArchetype("sultanbulb");

        assertEquals("sultanbulb", lampConfig.getArchetype());
    }

    @Test
    public void testGetFunction() {
        lampConfig.setFunction("mixed");

        assertEquals("mixed", lampConfig.getFunction());
    }

    @Test
    public void testGetDirection() {
        lampConfig.setDirection("Omnidirectional");

        assertEquals("Omnidirectional", lampConfig.getDirection());
    }

    @Test
    public void testGetAdditionalProperties() {
        lampConfig.setAdditionalProperty("property", 10);

        assertEquals(10, lampConfig.getAdditionalProperties().get("property"));
    }
}