package com.apigroups.api.repo;

import java.util.List;

import com.apigroups.api.requests.Projects;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Projectrepo extends MongoRepository<Projects, String> {
    List<Projects> findByProjectapi(String projectapi);
}