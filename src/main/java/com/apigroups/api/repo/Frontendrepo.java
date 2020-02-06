package com.apigroups.api.repo;

import com.apigroups.api.requests.Frontendcomponents;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Frontendrepo extends MongoRepository<Frontendcomponents , String> {
    
}
