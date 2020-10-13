package com.cognizant.dashboard.collectors.github.service;

import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;
import com.cognizant.dashboard.collectors.github.db.repo.CustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class GitHubDataService {
    @Autowired
    CustomRepository customRepository;

    public String getLastCommitDate(String projectName) {
        String since = "";
        List<GitHubRepos> lastCommittedDateQuery = customRepository.findLastCommittedDateQuery(projectName);
        if (!CollectionUtils.isEmpty(lastCommittedDateQuery)) {
            since = lastCommittedDateQuery.get(0).getCommitsList().get(0).getCommit().getCommitter().getDate().toInstant().toString();
        }
        log.info("Since Date is: {}", since);
        return since;
    }

}
