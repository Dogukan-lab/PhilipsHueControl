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
        "1",
        "2",
        "3"
})
/*
this class is used to hold all lamps that are retrieved when doing a search for lights
this class is the response from the get lights request
 */
public class HueInfo {

    @JsonProperty("1")
    private LampProduct product;

    @JsonProperty("2")
    private LampProduct product2;

    @JsonProperty("3")
    private LampProduct product3;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1")
    public LampProduct getProduct() {
        return product;
    }

    @JsonProperty("1")
    public void setProduct(LampProduct product) {
        this.product = product;
    }

    @JsonProperty("2")
    public LampProduct getProduct2() {
        return product2;
    }

    @JsonProperty("2")
    public void setProduct2(LampProduct product) {
        this.product2 = product;
    }

    @JsonProperty("3")
    public LampProduct getProduct3() {
        return product3;
    }

    @JsonProperty("3")
    public void setProduct3(LampProduct product) {
        this.product3 = product;
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

