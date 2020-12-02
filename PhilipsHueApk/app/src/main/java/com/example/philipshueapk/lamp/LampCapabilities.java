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
        "certified",
        "control",
        "streaming"
})
public class LampCapabilities {
    @JsonProperty("certified")
    private Boolean certified;
    @JsonProperty("control")
    private LampControl control;
    @JsonProperty("streaming")
    private LampStreaming streaming;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("certified")
    public Boolean getCertified() {
        return certified;
    }

    @JsonProperty("certified")
    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    @JsonProperty("control")
    public LampControl getControl() {
        return control;
    }

    @JsonProperty("control")
    public void setControl(LampControl control) {
        this.control = control;
    }

    @JsonProperty("streaming")
    public LampStreaming getStreaming() {
        return streaming;
    }

    @JsonProperty("streaming")
    public void setStreaming(LampStreaming streaming) {
        this.streaming = streaming;
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
