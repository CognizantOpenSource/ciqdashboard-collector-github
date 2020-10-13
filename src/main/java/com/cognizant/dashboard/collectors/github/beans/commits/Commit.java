package com.cognizant.dashboard.collectors.github.beans.commits;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "author",
        "committer",
        "message",
        "tree",
        "url",
        "comment_count",
        "verification"
})
@Data
public class Commit {

    @JsonProperty("author")
    public CommitAuthor author;
    @JsonProperty("committer")
    public Committer committer;
    @JsonProperty("message")
    public String message;
    @JsonProperty("tree")
    public Tree tree;
    @JsonProperty("url")
    public String url;
    @JsonProperty("comment_count")
    public Integer commentCount;
    @JsonProperty("verification")
    public Verification verification;
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