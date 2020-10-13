package com.cognizant.dashboard.collectors.github.component;

import com.cognizant.dashboard.collectors.github.client.GitHubClient;
import com.cognizant.dashboard.collectors.github.constants.Constants;
import com.cognizant.dashboard.collectors.github.beans.commits.Commits;
import com.cognizant.dashboard.collectors.github.beans.pullrequest.PullRequest;
import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;
import com.cognizant.dashboard.collectors.github.db.repo.GitHubRepoRepository;
import com.cognizant.dashboard.collectors.github.service.GitHubDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class GitHubComponent {

    @Autowired
    private GitHubClient gitHubClient;
    @Autowired
    private GitHubDataService gitHubDataService;
    @Autowired
    private GitHubRepoRepository gitHubRepoRepository;
    private HttpHeaders requestHeader = new HttpHeaders();
    @Autowired
    private  GithubCommonUtility commonUtility;
    @PostConstruct
    public HttpHeaders postConstructMethod(){
        return requestHeader = commonUtility.getHeaders();
    }

    public List<GitHubRepos> getRepos() {
        List<GitHubRepos> gitHubRepository = gitHubClient.getRepositories(requestHeader);
        return gitHubRepository;
    }

    List<Commits> updateCommits(String ownerName, String repoName, String since) {
        List<Commits> commitsList = new ArrayList<>();
        int pageIndex = 1;
        boolean hasMorePages = true;
        while (hasMorePages) {
            List<Commits> commits = getAllCommits(ownerName, repoName, pageIndex, since);
            commitsList.addAll(commits);
            if (commits.size() <= 0 || commits.size() < Constants.MAX_PER_PAGE_ITEMS) {
                hasMorePages = false;
                continue;
            }
            pageIndex++;
        }
        return commitsList;
    }

    List<Commits> getAllCommits(String ownerName, String repoName, int pageIndex, String since) {
        Map<String, String> requestParamMap = new HashMap();
        requestParamMap.put(Constants.PER_PAGE, String.valueOf(Constants.MAX_PER_PAGE_ITEMS));
        requestParamMap.put(Constants.PAGE, String.valueOf(pageIndex));
        requestParamMap.put(Constants.SINCE, since);
        List<Commits> commitsList = gitHubClient.getAllCommits(requestParamMap, ownerName, repoName,requestHeader);
        commitsList.forEach(commits -> {
            Commits commitsDetails = gitHubClient.getCommits(ownerName, repoName,commits.getSha(),requestHeader);
            commits.setFiles(commitsDetails.getFiles());
            commits.setStats(commitsDetails.getStats());
        });
        return commitsList;
    }

    public void getGitHubReposData() {
        List<GitHubRepos> reposList = getRepos();
        reposList.forEach(repo -> {
            String lastCommitDate = gitHubDataService.getLastCommitDate(repo.getName());
            log.info("Collecting Commit Details for " + repo.getOwner().getLogin() + " " + repo.getName());
            List<Commits> commits = updateCommits(repo.getOwner().getLogin(), repo.getName(), lastCommitDate);
            repo.setCommitsList(commits);
            log.info("Collecting PullRequest Details for " + repo.getOwner().getLogin() + " " + repo.getName());
            List<PullRequest> pullRequestList = getPullRequest(repo.getOwner().getLogin(), repo.getName());
            repo.setPullRequests(pullRequestList);
        });
        gitHubRepoRepository.saveAll(reposList);
    }

    public List<PullRequest> getPullRequest(String owner, String repo) {
        List<PullRequest> allPullRequestList = new ArrayList<>();
        int pageIndex = 1;
        boolean hasMorePages = true;
        Map<String, String> requestParamMap = new HashMap();
        requestParamMap.put(Constants.PER_PAGE, String.valueOf(Constants.MAX_PER_PAGE_ITEMS));
        requestParamMap.put("state", "all");
        while (hasMorePages) {
            requestParamMap.put(Constants.PAGE, String.valueOf(pageIndex));
            List<PullRequest> pullRequestList = gitHubClient.getPullRequests(requestParamMap, owner, repo,requestHeader);
            allPullRequestList.addAll(pullRequestList);
            if (pullRequestList.size() <= 0 || pullRequestList.size() < Constants.MAX_PER_PAGE_ITEMS) {
                hasMorePages = false;
                continue;
            }
            pageIndex++;
        }
        return allPullRequestList;
    }

}
