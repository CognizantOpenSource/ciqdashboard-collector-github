package com.cognizant.dashboard.collectors.github.beans.repos;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "admin",
        "push",
        "pull"
})
public class Permissions {

    @JsonProperty("admin")
    private Boolean admin;
    @JsonProperty("push")
    private Boolean push;
    @JsonProperty("pull")
    private Boolean pull;
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
