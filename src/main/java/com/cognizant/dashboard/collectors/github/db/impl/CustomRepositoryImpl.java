package com.cognizant.dashboard.collectors.github.db.impl;

import com.cognizant.dashboard.collectors.github.beans.repos.GitHubRepos;
import com.cognizant.dashboard.collectors.github.db.repo.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    private MongoTemplate template;
    @Autowired
    public CustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.template = mongoTemplate;
    }

    @Override
    public List<GitHubRepos> findLastCommittedDateQuery(String projectName) {
        return findByFieldInOrder(projectName, Sort.Direction.DESC, "commit.committer.date");
    }

    private List<GitHubRepos> findByFieldInOrder(String projectName, Sort.Direction direction, String fieldName){
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(projectName);

        query.addCriteria(criteria);
        return template.find(query, GitHubRepos.class);
    }
}
