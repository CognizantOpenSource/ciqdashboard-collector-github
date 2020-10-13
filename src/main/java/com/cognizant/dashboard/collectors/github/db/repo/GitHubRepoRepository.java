package com.cognizant.dashboard.collectors.github.db.repo;

import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GitHubRepoRepository extends MongoRepository<GitHubRepos, String> {

}
