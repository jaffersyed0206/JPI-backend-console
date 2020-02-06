package com.apigroups.api.repo;


import com.apigroups.api.requests.Components;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Componentrepo extends MongoRepository<Components, String> {
}