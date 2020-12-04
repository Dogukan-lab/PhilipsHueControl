package com.example.philipshueapk.lamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampCtTest {
    LampCt lampCt;

    @BeforeEach
    public void setUp() {
        lampCt = new LampCt();
    }

    @Test
    void getMin() {
        lampCt.setMin(2);
        assertEquals(2,lampCt.getMin());
    }

    @Test
    void getMax() {
        lampCt.setMax(700);
        assertEquals(700,lampCt.getMax());
    }

    @Test
    void getAdditionalProperties() {
        lampCt.setAdditionalProperty("Test prop",true);
        assertTrue( (Boolean) lampCt.getAdditionalProperties().get("Test prop"));
    }
}