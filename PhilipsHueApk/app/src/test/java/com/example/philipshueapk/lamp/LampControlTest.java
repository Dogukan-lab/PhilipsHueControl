package com.example.philipshueapk.lamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LampControlTest {
    LampControl lampControl;

    @BeforeEach
    void setUp() {
        this.lampControl = new LampControl();
    }

    @Test
    void getMindimlevel() {
        this.lampControl.setMindimlevel(12);
        assertEquals(12,lampControl.getMindimlevel());
    }

    @Test
    void getMaxlumen() {
        lampControl.setMaxlumen(69);
        assertEquals(69,lampControl.getMaxlumen());
    }

    @Test
    void getColorgamuttype() {
        lampControl.setColorgamuttype("type 1");
        assertEquals("type 1",lampControl.getColorgamuttype());
    }

    @Test
    void getColorgamut() {

        List<List<Double>> colorGamut = new ArrayList<>();
        colorGamut.add(Arrays.asList(0.654,0.444));
        colorGamut.add(Arrays.asList(0.409,0.518));
        colorGamut.add(Arrays.asList(0.167,0.04));

        lampControl.setColorgamut(colorGamut);

        List<Double> l1 = Arrays.asList(0.654,0.444);
        List<Double> l2 = Arrays.asList(0.409,0.518);
        List<Double> l3 = Arrays.asList(0.167,0.04);

        assertArrayEquals(l1.toArray(),lampControl.getColorgamut().get(0).toArray());
        assertArrayEquals(l2.toArray(),lampControl.getColorgamut().get(1).toArray());
        assertArrayEquals(l3.toArray(),lampControl.getColorgamut().get(2).toArray());

    }

    @Test
    void getCt() {
        LampCt lampCt = new LampCt();
        lampCt.setMax(10);
        lampCt.setMin(3);
        lampCt.setAdditionalProperty("ct property","hello there");

        lampControl.setCt(lampCt);

        assertNotNull(lampControl.getCt());
        assertEquals(10,lampControl.getCt().getMax());
        assertEquals(3,lampControl.getCt().getMin());

        assertEquals("hello there",lampControl.getCt().getAdditionalProperties().get("ct property"));
    }

    @Test
    void getAdditionalProperties() {
        lampControl.setAdditionalProperty("Test", 300);
        assertEquals(300, lampControl.getAdditionalProperties().get("Test"));
    }
}