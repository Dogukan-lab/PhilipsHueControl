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
        "renderer",
        "proxy"
})

public class LampStreaming {
    @JsonProperty("renderer")
    private Boolean renderer;
    @JsonProperty("proxy")
    private Boolean proxy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("renderer")
    public Boolean getRenderer() {
        return renderer;
    }

    @JsonProperty("renderer")
    public void setRenderer(Boolean renderer) {
        this.renderer = renderer;
    }

    @JsonProperty("proxy")
    public Boolean getProxy() {
        return proxy;
    }

    @JsonProperty("proxy")
    public void setProxy(Boolean proxy) {
        this.proxy = proxy;
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
