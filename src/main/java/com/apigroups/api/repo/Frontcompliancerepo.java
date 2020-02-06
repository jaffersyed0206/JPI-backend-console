package com.apigroups.api.repo;

import com.apigroups.api.requests.Frontendcompliance;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Frontcompliancerepo extends MongoRepository<Frontendcompliance , String> {

}