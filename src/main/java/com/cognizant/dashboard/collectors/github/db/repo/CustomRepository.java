package com.cognizant.dashboard.collectors.github.db.repo;

import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;

import java.util.List;

public interface CustomRepository {

    List<GitHubRepos> findLastCommittedDateQuery(String projectName);
}
