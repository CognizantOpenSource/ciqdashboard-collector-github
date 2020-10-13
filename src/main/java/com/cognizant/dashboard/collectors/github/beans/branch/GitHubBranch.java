package com.cognizant.dashboard.collectors.github.beans.branch;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "commit",
        "protected"
})
@Data
public class GitHubBranch {

    @JsonProperty("name")
    private String name;
    @JsonProperty("commit")
    private Commit commit;
    @JsonProperty("protected")
    private Boolean _protected;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
