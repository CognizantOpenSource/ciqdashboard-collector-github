package com.cognizant.dashboard.collectors.github;

import com.cognizant.dashboard.collectors.github.service.GitHubDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	GitHubDataService gitHubDataService;
	@Test
	void contextLoads() {
	}

	@Test
	void getCommitDate()
	{
		gitHubDataService.getLastCommitDate("demoproject");
	}
}
