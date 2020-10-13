package com.cognizant.dashboard.collectors.github.beans.commits;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "verified",
        "reason",
        "signature",
        "payload"
})
public class Verification {

    @JsonProperty("verified")
    public Boolean verified;
    @JsonProperty("reason")
    public String reason;
    @JsonProperty("signature")
    public Object signature;
    @JsonProperty("payload")
    public Object payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}