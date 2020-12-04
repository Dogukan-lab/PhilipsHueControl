package com.example.philipshueapk.lamp;

import junit.framework.TestCase;
import org.junit.jupiter.api.*;

public class LampProductTest extends TestCase {

    private LampProduct lampProduct;

    @BeforeEach
    public void setUp() throws Exception {
        lampProduct = new LampProduct();
    }

    @AfterEach
    public void tearDown() throws Exception {
        lampProduct = null;
    }

    @Test
    public void testGetState() {
        LampState lampState = new LampState();
        lampState.setHue(4444);
        lampProduct.setState(lampState);

        assertNotNull(lampProduct.getState());
        assertEquals(4444, lampProduct.getState().getHue().intValue());
    }

    @Test
    public void testGetSwupdate() {
        LampSwUpdate swUpdate = new LampSwUpdate();
        swUpdate.setState("ON");
        lampProduct.setSwupdate(swUpdate);

        assertNotNull(lampProduct.getSwupdate());
        assertEquals("ON", lampProduct.getSwupdate().getState());
    }

    @Test
    public void testGetType() {
        lampProduct.setType("Motorola xF");

        assertEquals("Motorola xF", lampProduct.getType());
    }

    @Test
    public void testGetName() {
        lampProduct.setName("BedroomLights");

        assertEquals("BedroomLights", lampProduct.getName());
    }

    @Test
    public void testGetModelid() {
        String modelId = "asdhs*73ssa";
        lampProduct.setModelid(modelId);

        assertEquals(modelId, lampProduct.getModelid());
    }

    @Test
    public void testGetManufacturername() {
        lampProduct.setManufacturername("Intel");

        assertEquals("Intel", lampProduct.getManufacturername());
    }

    @Test
    public void testGetProductname() {
        lampProduct.setProductname("Omnidirectional lamp");

        assertEquals("Omnidirectional lamp", lampProduct.getProductname());
    }

    @Test
    public void testGetCapabilities() {
        LampCapabilities capabilities = new LampCapabilities();
        capabilities.setCertified(true);
        lampProduct.setCapabilities(capabilities);

        assertNotNull(lampProduct.getCapabilities());
        assertTrue(lampProduct.getCapabilities().getCertified());
    }

    @Test
    public void testGetConfig() {
        LampConfig config = new LampConfig();
        config.setDirection("Omnidirectional");
        lampProduct.setConfig(config);

        assertNotNull(lampProduct.getConfig());
        assertEquals("Omnidirectional", lampProduct.getConfig().getDirection());
    }

    @Test
    public void testGetUniqueid() {
        lampProduct.setUniqueid("uniqueId078");

        assertEquals("uniqueId078", lampProduct.getUniqueid());
    }

    @Test
    public void testGetSwversion() {
        lampProduct.setSwversion("v1.0");

        assertEquals("v1.0", lampProduct.getSwversion());
    }

    @Test
    public void testGetAdditionalProperties() {
        lampProduct.setAdditionalProperty("lampLocation", "Some place in your house");

        assertEquals("Some place in your house", lampProduct.getAdditionalProperties().get("lampLocation"));
    }
}