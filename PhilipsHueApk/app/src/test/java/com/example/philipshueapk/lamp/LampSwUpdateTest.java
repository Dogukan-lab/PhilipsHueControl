package com.example.philipshueapk.lamp;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class LampSwUpdateTest {
    private LampSwUpdate lampSwUpdate;

    @BeforeEach
    public void setUp() throws Exception {
        lampSwUpdate = new LampSwUpdate();
    }

    @Test
    public void getState() {
        String state = "TestState";
        lampSwUpdate.setState(state);
        assertEquals("TestState",lampSwUpdate.getState());
    }

    @Test
    public void getLastinstall() {
        String lastInstall = "2018-01-02T19:24:20";
        lampSwUpdate.setLastinstall(lastInstall);
        assertEquals(lastInstall,lampSwUpdate.getLastinstall());
    }

    @Test
    public void getAdditionalProperties() {

    }
}