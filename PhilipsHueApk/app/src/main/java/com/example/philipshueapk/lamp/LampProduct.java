package com.example.philipshueapk.lamp;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "state",
        "swupdate",
        "type",
        "name",
        "modelid",
        "manufacturername",
        "productname",
        "capabilities",
        "config",
        "uniqueid",
        "swversion"
})


/*
 * Holds an actual lamp, this class is used to retrieve and change individual lamp settings
 */
public class LampProduct {


    @JsonProperty("state")
    private LampState state;
    @JsonProperty("swupdate")
    private LampSwUpdate swupdate;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("modelid")
    private String modelid;
    @JsonProperty("manufacturername")
    private String manufacturername;
    @JsonProperty("productname")
    private String productname;
    @JsonProperty("capabilities")
    private LampCapabilities capabilities;
    @JsonProperty("config")
    private LampConfig config;
    @JsonProperty("uniqueid")
    private String uniqueid;
    @JsonProperty("swversion")
    private String swversion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("state")
    public LampState getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(LampState state) {
        this.state = state;
    }

    @JsonProperty("swupdate")
    public LampSwUpdate getSwupdate() {
        return swupdate;
    }

    @JsonProperty("swupdate")
    public void setSwupdate(LampSwUpdate swupdate) {
        this.swupdate = swupdate;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("modelid")
    public String getModelid() {
        return modelid;
    }

    @JsonProperty("modelid")
    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    @JsonProperty("manufacturername")
    public String getManufacturername() {
        return manufacturername;
    }

    @JsonProperty("manufacturername")
    public void setManufacturername(String manufacturername) {
        this.manufacturername = manufacturername;
    }

    @JsonProperty("productname")
    public String getProductname() {
        return productname;
    }

    @JsonProperty("productname")
    public void setProductname(String productname) {
        this.productname = productname;
    }

    @JsonProperty("capabilities")
    public LampCapabilities getCapabilities() {
        return capabilities;
    }

    @JsonProperty("capabilities")
    public void setCapabilities(LampCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    @JsonProperty("config")
    public LampConfig getConfig() {
        return config;
    }

    @JsonProperty("config")
    public void setConfig(LampConfig config) {
        this.config = config;
    }

    @JsonProperty("uniqueid")
    public String getUniqueid() {
        return uniqueid;
    }

    @JsonProperty("uniqueid")
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    @JsonProperty("swversion")
    public String getSwversion() {
        return swversion;
    }

    @JsonProperty("swversion")
    public void setSwversion(String swversion) {
        this.swversion = swversion;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
