package com.cognizant.dashboard.collectors.github.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "github")
public class GitHubProperties {
    private String url;
    private String token;
}
