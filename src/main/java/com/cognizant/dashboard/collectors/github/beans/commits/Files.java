package com.cognizant.dashboard.collectors.github.beans.commits;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sha",
        "filename",
        "status",
        "additions",
        "deletions",
        "changes",
        "blob_url",
        "raw_url",
        "contents_url",
        "patch"
})
public class Files {

    @JsonProperty("sha")
    public String sha;
    @JsonProperty("filename")
    public String filename;
    @JsonProperty("status")
    public String status;
    @JsonProperty("additions")
    public Integer additions;
    @JsonProperty("deletions")
    public Integer deletions;
    @JsonProperty("changes")
    public Integer changes;
    @JsonProperty("blob_url")
    public String blobUrl;
    @JsonProperty("raw_url")
    public String rawUrl;
    @JsonProperty("contents_url")
    public String contentsUrl;
    @JsonProperty("patch")
    public String patch;
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