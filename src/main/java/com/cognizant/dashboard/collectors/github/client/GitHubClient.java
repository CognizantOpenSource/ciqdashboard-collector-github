package com.cognizant.dashboard.collectors.github.client;


import com.cognizant.dashboard.collectors.github.beans.branch.GitHubBranch;
import com.cognizant.dashboard.collectors.github.beans.commits.Commits;
import com.cognizant.dashboard.collectors.github.beans.pullrequest.PullRequest;
import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface GitHubClient {

        @GetMapping("/user/repos") // type = member, Default type=all
        List<GitHubRepos> getRepositories(@RequestHeader HttpHeaders requestHeader);

        @GetMapping("/repos/{ownerName}/{projectName}/branches")
        List<GitHubBranch> getBranches(@PathVariable("ownerName") Object ownerName,
                                       @PathVariable("projectName") Object projectName,
                                       @RequestHeader HttpHeaders requestHeader);

        ///repos/:owner/:repo/commits?since=YYYY-MM-DDTHH:MM:SSZ&until=YYYY-MM-DDTHH:MM:SSZ
        @GetMapping("/repos/{ownerName}/{projectName}/commits/{shaId}")
        Commits getCommits(@PathVariable("ownerName") Object ownerName,
                           @PathVariable("projectName") Object projectName,
                           @PathVariable("shaId") Object shaId,
                           @RequestHeader HttpHeaders requestHeader);

        @GetMapping("/repos/{owner}/{repo}/commits")
        List<Commits> getAllCommits(@RequestParam Map<String, String> requestParams,
                                   @RequestParam("owner") String owner,
                                   @RequestParam("repo") String repo,
                                    @RequestHeader HttpHeaders requestHeader
                                );


        @GetMapping("/repos/{owner}/{repo}/pulls")
        List<PullRequest> getPullRequests(@RequestParam Map<String, String> requestParams,
                                          @RequestParam("owner") String owner,
                                          @RequestParam("repo") String repo,
                                          @RequestHeader HttpHeaders requestHeader);

}



