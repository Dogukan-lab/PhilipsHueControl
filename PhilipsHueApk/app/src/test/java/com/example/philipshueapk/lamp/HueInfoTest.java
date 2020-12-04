package com.example.philipshueapk.lamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HueInfoTest {
    HueInfo hueInfo;
    LampProduct product1;
    LampProduct product2;
    LampProduct product3;

    @BeforeEach
    void setUp() {
        hueInfo = new HueInfo();
        product1 = new LampProduct();
        product1.setName("product 1");
        product1.setModelid("model 1");
        LampCapabilities capabilities = new LampCapabilities();
        capabilities.setCertified(true);
        product1.setCapabilities(capabilities);

        product2 = new LampProduct();
        product2.setName("product 2");
        product2.setModelid("model 2");
        LampCapabilities capabilities1 = new LampCapabilities();
        capabilities1.setCertified(true);
        product2.setCapabilities(capabilities1);

        product3 = new LampProduct();
        product3.setName("product 3");
        product3.setModelid("model 3");
        LampCapabilities capabilities2 = new LampCapabilities();
        capabilities2.setCertified(false);
        product3.setCapabilities(capabilities2);
    }

    @Test
    void testProduct() {
        hueInfo.setProduct(product1);

        assertNotNull(hueInfo.getProduct());
        assertEquals("product 1",hueInfo.getProduct().getName());
        assertEquals("model 1",hueInfo.getProduct().getModelid());
        assertTrue(hueInfo.getProduct().getCapabilities().getCertified());
    }

    @Test
    void testProduct2() {
        hueInfo.setProduct2(product2);

        assertNotNull(hueInfo.getProduct2());
        assertEquals("product 2",hueInfo.getProduct2().getName());
        assertEquals("model 2",hueInfo.getProduct2().getModelid());
        assertTrue(hueInfo.getProduct2().getCapabilities().getCertified());
    }

    @Test
    void testProduct3() {
        hueInfo.setProduct3(product3);

        assertNotNull(hueInfo.getProduct3());
        assertEquals("product 3",hueInfo.getProduct3().getName());
        assertEquals("model 3",hueInfo.getProduct3().getModelid());
        assertFalse(hueInfo.getProduct3().getCapabilities().getCertified());
    }

    @Test
    void testAdditionalProperties() {

        hueInfo.setAdditionalProperty("additional properties",1.654f);
        assertEquals(1.654f,hueInfo.getAdditionalProperties().get("additional properties"));
    }

    @Test
    void testLamps() {
        ArrayList<LampProduct> lamps = hueInfo.getLamps();
        assertNotNull(lamps);
        assertEquals(3,lamps.size());
    }
}