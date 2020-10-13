package com.cognizant.dashboard.collectors.github.beans.commits;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total",
        "additions",
        "deletions"
})
public class Stats {

    @JsonProperty("total")
    public Integer total;
    @JsonProperty("additions")
    public Integer additions;
    @JsonProperty("deletions")
    public Integer deletions;
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