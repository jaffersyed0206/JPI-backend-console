package com.apigroups.api.repo;

import java.util.List;

import com.apigroups.api.requests.Users;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Userrepo extends MongoRepository<Users, String> {
    List<Users> findByUsernameAndPassword(String username, String Password);
    List<Users> findByAuthtoken(String authtoken);
    List<Users> findByUsername(String username);
    List<Users> findByPassword(String password);
}