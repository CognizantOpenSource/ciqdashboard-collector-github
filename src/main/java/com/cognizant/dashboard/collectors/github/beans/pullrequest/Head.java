package com.cognizant.dashboard.collectors.github.beans.pullrequest;

import java.util.HashMap;
import java.util.Map;

import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;
import com.cognizant.dashboard.collectors.github.beans.repos.Owner;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "label",
        "ref",
        "sha",
        "user",
        "repo"
})
public class Head {

    @JsonProperty("label")
    public String label;
    @JsonProperty("ref")
    public String ref;
    @JsonProperty("sha")
    public String sha;
    @JsonProperty("user")
    public Owner user;
    @JsonProperty("repo")
    public GitHubRepos repo;
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