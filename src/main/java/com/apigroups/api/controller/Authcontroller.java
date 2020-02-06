package com.apigroups.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.apigroups.api.functions.GeneratorId;
import com.apigroups.api.repo.Userrepo;
import com.apigroups.api.requests.Pendingrequests;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/authentication")
@RestController
public class Authcontroller {
    @Autowired
    private Userrepo userrepo;

    @PostMapping("/createuser")
    public Object createUser(@RequestBody Users request) {
        Users newuser = new Users();
        List<Users> checkerusername = userrepo.findByUsername(request.getUsername());
        List<Users> checkerpassword = userrepo.findByPassword(request.getPassword());
        if (checkerusername.size() == 0 || checkerpassword.size() == 0) {
            List<Projects> setProjects = new ArrayList<>();
            List<Pendingrequests> setPendingRequests = new ArrayList<>();
            newuser.setUsername(request.getUsername());
            newuser.setFirstname(request.getFirstname());
            newuser.setLastname(request.getLastname());
            newuser.setEmail(request.getEmail());
            newuser.setPassword(request.getPassword());
            newuser.setAuthenticated(false);
            newuser.setProjects(setProjects);
            newuser.setPendingrequests(setPendingRequests);
            newuser.setSuspended(false);
            newuser.setAuthtoken("");
            userrepo.save(newuser);
            Map<String, Object> response = new HashMap<>();
            response.put("username", request.getUsername());
            response.put("password", request.getPassword());
            return response;
        } else {
            return new ResponseEntity<String>("Username or password has already been taken", HttpStatus.OK);
        }
    }

    @PostMapping("/authenticateprocess")
    public String LoginUser(@RequestBody Users request) {
        List<Users> myIterator = userrepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (myIterator.size() == 1) {       
         String authtoken = GeneratorId.GenerateId(150);
         Users currentuser = myIterator.get(0);
         currentuser.setAuthenticated(true);
         currentuser.setAuthtoken(authtoken);
         userrepo.save(currentuser);
         return authtoken;
         } 
        else {
         return "error";
        }
    }

    
    
    @GetMapping("/login/{authtoken}")
    public Object AuthenticateUser(@PathVariable String authtoken) {
        List<Users> checkLoggedIn = userrepo.findByAuthtoken(authtoken);
        if (checkLoggedIn.size() != 0) {
            Users currentuser =  checkLoggedIn.get(0);
            if (currentuser.getAuthenticated().equals(true) != false) {
                Map<String, Object> response = new HashMap<>();
                response.put("firstname", currentuser.getFirstname());
                response.put("lastname", currentuser.getLastname());
                response.put("email", currentuser.getEmail());
                response.put("username", currentuser.getUsername());
                response.put("userid", currentuser.getId());
                currentuser.setAuthtoken("");
                userrepo.save(currentuser);
                return response;
            } else {
                return new ResponseEntity<String>("error" , HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>("error: more than one user with the same authtoken", HttpStatus.OK);
        }
    }

    @PutMapping("/logout/{useruid}")
    public Object Logout(@PathVariable String useruid) {
        Optional<Users> currentuser = userrepo.findById(useruid);
        if (currentuser.isPresent()) {
            currentuser.get().setAuthenticated(false);
            currentuser.get().setAuthtoken("");
            userrepo.save(currentuser.get());
        }
        return null;
    }
}




/*

Map<String ,Object> response = new HashMap<>();
response.put("firstname", myIterator.get(0).getFirstname());
response.put("lastname", myIterator.get(0).getLastname());
response.put("email", myIterator.get(0).getEmail());
response.put("username", myIterator.get(0).getUsername());
response.put("userid", myIterator.get(0).getId());
Users authuser = new Users();
authuser.setAuthenticated(true);
response.put("authentication", authuser.getAuthenticated());
return response;*/
