package com.apigroups.api.repo;

import com.apigroups.api.requests.Backendcomponents;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Backendrepo extends MongoRepository<Backendcomponents , String> {

}