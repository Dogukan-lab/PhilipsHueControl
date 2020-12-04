package com.example.philipshueapk.lamp;

import android.graphics.Color;
import android.util.Log;

import junit.framework.TestCase;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class LampStateTest extends TestCase {

    private LampState lampState;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        lampState = new LampState();
    }

    @AfterEach
    public void tearDown() {
        lampState = null;
    }

    @Test
    public void testGetOn() {
        lampState.setOn(true);

        assertTrue(lampState.getOn());
    }

    @Test
    public void testGetBri() {
        lampState.setBri(200);

        assertEquals(200, lampState.getBri().intValue());
    }

    @Test
    public void testGetHue() {
        lampState.setHue(4444);

        assertEquals(4444, lampState.getHue().intValue());
    }

    @Test
    public void testGetSat() {
        lampState.setSat(254);

        assertEquals(254, lampState.getSat().intValue());
    }

    @Test
    public void testGetEffect() {
        lampState.setEffect("Disco");

        assertEquals("Disco", lampState.getEffect());
    }

    @Test
    public void testGetXy() {
        List<Double> xy = new ArrayList<>();
        xy.add(0.345);
        xy.add(0.3568);
        lampState.setXy(xy);

        assertEquals(0.345, lampState.getXy().get(0));
        assertEquals(0.3568,lampState.getXy().get(1));
    }

    @Test
    public void testGetCt() {
        lampState.setCt(40);

        assertEquals(40, lampState.getCt().intValue());
    }

    @Test
    public void testGetAlert() {
        String alert = "Not connected";
        lampState.setAlert(alert);

        assertEquals(alert, lampState.getAlert());
    }

    @Test
    public void testGetColormode() {
        lampState.setColormode("hs");

        assertEquals("hs", lampState.getColormode());
    }

    @Test
    public void testGetMode() {
        lampState.setMode("homeautomation");

        assertEquals("homeautomation", lampState.getMode());
    }

    @Test
    public void testGetReachable() {
        lampState.setReachable(true);

        assertTrue(lampState.getReachable());
    }

    @Test
    public void testGetAdditionalProperties() {
        lampState.setAdditionalProperty("lampOne", 20);

        assertEquals(20, lampState.getAdditionalProperties().get("lampOne"));
    }
}