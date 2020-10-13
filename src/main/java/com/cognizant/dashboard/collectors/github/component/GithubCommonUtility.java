package com.cognizant.dashboard.collectors.github.component;

import com.cognizant.dashboard.collectors.github.beans.GitHubProperties;
import com.cognizant.dashboard.collectors.github.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class GithubCommonUtility {
    HttpHeaders headers = new HttpHeaders();
    @Autowired
    GitHubProperties gitHubProperties;

    @PostConstruct
    public void postConstructMethod(){
        headers.set(Constants.AUTHORIZATION,"Bearer "+gitHubProperties.getToken());
    }

    public HttpHeaders getHeaders() {
        return headers;
    }
}
