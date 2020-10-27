package com.cognizant.dashboard.collectors.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableAutoConfiguration
@EnableEncryptableProperties
public class GitHubCollectorApplication {
	public static void main(String[] args) {
		SpringApplication.run(GitHubCollectorApplication.class, args);
	}
}
