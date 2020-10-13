package com.cognizant.dashboard.collectors.github.config;

import com.cognizant.dashboard.collectors.github.client.GitHubClient;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@Slf4j
public class GlobalConfiguration {

    private static final String AUTHORIZATION = "Authorization";
    @Value("${github.url}")
    private String githubUrl;
    @Value("Bearer ${github.token}")
    private String githubToken;


    @Bean
    public GitHubClient gitHubClient() {

        return Feign.builder()
                .contract(new SpringMvcContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .options(new Request.Options(20000, TimeUnit.MILLISECONDS, 20000, TimeUnit.MILLISECONDS, true))
                .logger(new Slf4jLogger(GitHubClient.class))
                .logLevel(Logger.Level.FULL)
                .target(GitHubClient.class,githubUrl);
    }

    public RequestInterceptor requestHeaderTokenInterceptor(String authKey, String token) {
        return requestTemplate -> requestTemplate.header(authKey, token);
    }

}


