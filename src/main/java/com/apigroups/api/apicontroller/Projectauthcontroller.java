package com.apigroups.api.apicontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apigroups.api.functions.Finder;
import com.apigroups.api.functions.GeneratorId;
import com.apigroups.api.functions.jpiauth.Usersfinder;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.authentication.Projectauthentication;
import com.apigroups.api.requests.authentication.users.Projectusers;
import com.apigroups.api.requests.authentication.users.authmethod.Setauthmethod;
import com.apigroups.api.requests.authentication.users.editusers.Editusers;
import com.apigroups.api.requests.authentication.users.registerforms.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/projectauth")
@RestController
public class Projectauthcontroller {
    @Autowired
    private Projectrepo projectrepo;
    @GetMapping("/getprojectauthcreds/{projectapi}")
    public Object GetProjectCredentials(@PathVariable String projectapi) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentauth = currentproject.getAuthenticationprojects();
        List<Object> responseobject = new ArrayList<>();
        for (Projectusers docs: currentauth.getUsers()) {
            Map<String , Object> userjson = new HashMap<>();
            userjson.put("useruid", docs.getUseruid());
            userjson.put("response", docs.getVisuals());
            responseobject.add(userjson);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("users", responseobject);
        response.put("authmethod", currentauth.getAuthmethod());
        response.put("labels", currentauth.getLabels());
        response.put("tracking" , currentauth.getTracking());
        response.put("trackinglabels", currentauth.getLabels().subList(5, currentauth.getLabels().size()));

        return response;
    }
    @PostMapping("/addusertoproject/{projectapi}")
    public Object AddUsersToProject(@PathVariable String projectapi , @RequestBody Register request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentAP = currentproject.getAuthenticationprojects();
        List<Projectusers> allProjectUsers = currentAP.getUsers();
        if (currentAP.getAuthmethod().equals("Email and Password") == true) {
            Projectusers currentuser = Usersfinder.GetProjectEmailandPassUser(allProjectUsers, request.getEmail(), request.getPassword());
            Map<String , Object> response = new HashMap<>();
            if (currentuser == null) {
                currentAP.setAmountOfUsers(currentAP.getAmountOfUsers()  + 1);
                String userid = GeneratorId.GenerateId(60);
                Projectusers createuser = new Projectusers();
                createuser.setAuthenticated(false);
                createuser.setEmail(request.getEmail());
                createuser.setFirstname(request.getFirstname());
                createuser.setLastname(request.getLastname());
                createuser.setParameters(request.getMapJson());
                createuser.setPassword(request.getPassword());
                createuser.setUsername(request.getUsername());
                createuser.setUsertoken(request.getUsertoken());
                createuser.setVisuals(request.getVisuals());
                createuser.setUseruid(userid);
                allProjectUsers.add(createuser);
                projectrepo.save(currentproject);
                response.put("useruid", userid);
                response.put("response", request.getVisuals());
                return response;
            } else {
                response.put("error", "User already exists");
                return response;
            }
        } else if (currentAP.getAuthmethod().equals("Username and Password") == true) {
            Projectusers currentuser = Usersfinder.GetProjectUsernameandPassUser(allProjectUsers, request.getUsername(), request.getPassword());
            Map<String , Object> response = new HashMap<>();
            if (currentuser == null) {
                currentAP.setAmountOfUsers(currentAP.getAmountOfUsers()  + 1);
                String userid = GeneratorId.GenerateId(60);
                Projectusers createuser = new Projectusers();
                createuser.setAuthenticated(false);
                createuser.setEmail(request.getEmail());
                createuser.setFirstname(request.getFirstname());
                createuser.setLastname(request.getLastname());
                createuser.setParameters(request.getMapJson());
                createuser.setPassword(request.getPassword());
                createuser.setUsername(request.getUsername());
                createuser.setUsertoken(request.getUsertoken());
                createuser.setVisuals(request.getVisuals());
                createuser.setUseruid(userid);
                allProjectUsers.add(createuser);
                projectrepo.save(currentproject);
                response.put("useruid", userid);
                response.put("response", request.getVisuals());
                return response;
            } else {
                response.put("error", "User already exists");
                return response;
            }
        } else if (currentAP.getAuthmethod().equals("") == true) {
            Map<String , Object> response = new HashMap<>();
            if (request.getAuthmethod().equals("Email and Password") == true) {
                currentAP.setAuthmethod("Email and Password");
                Projectusers currentusers = Usersfinder.GetProjectEmailandPassUser(allProjectUsers, request.getEmail(), request.getPassword());
                if (currentusers == null) {
                    currentAP.setAmountOfUsers(currentAP.getAmountOfUsers()  + 1);
                    String userid = GeneratorId.GenerateId(60);
                    Projectusers createuser = new Projectusers();
                    createuser.setAuthenticated(false);
                    createuser.setEmail(request.getEmail());
                    createuser.setFirstname(request.getFirstname());
                    createuser.setLastname(request.getLastname());
                    createuser.setParameters(request.getMapJson());
                    createuser.setPassword(request.getPassword());
                    createuser.setUsername(request.getUsername());
                    createuser.setUsertoken(request.getUsertoken());
                    createuser.setVisuals(request.getVisuals());
                    createuser.setUseruid(userid);
                    allProjectUsers.add(createuser);
                    projectrepo.save(currentproject);
                    response.put("useruid", userid);
                    response.put("response", request.getVisuals());
                    response.put("authmethod", request.getAuthmethod());
                    return response;
                } else {
                    response.put("email", "User already exists");
                    return response;
                }
            } else if (request.getAuthmethod().equals("Username and Password") == true) {
                currentAP.setAuthmethod("Username and Password");
                Projectusers currentusers = Usersfinder.GetProjectUsernameandPassUser(allProjectUsers, request.getUsername(), request.getPassword());
                if (currentusers == null) {
                    currentAP.setAmountOfUsers(currentAP.getAmountOfUsers()  + 1);
                    String userid = GeneratorId.GenerateId(60);
                    Projectusers createuser = new Projectusers();
                    createuser.setAuthenticated(false);
                    createuser.setEmail(request.getEmail());
                    createuser.setFirstname(request.getFirstname());
                    createuser.setLastname(request.getLastname());
                    createuser.setParameters(request.getMapJson());
                    createuser.setPassword(request.getPassword());
                    createuser.setUsername(request.getUsername());
                    createuser.setUsertoken(request.getUsertoken());
                    createuser.setVisuals(request.getVisuals());
                    createuser.setUseruid(userid);
                    allProjectUsers.add(createuser);
                    projectrepo.save(currentproject);
                    response.put("useruid", userid);
                    response.put("response", request.getVisuals());
                    response.put("authmethod", request.getAuthmethod());
                    return response;
                } else {
                    response.put("email", "User already exists");
                    return response;
                }
            } else {
                response.put("error", "Please input a Authentication Method");                
                return response;
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "There was an internal server error");
            return response;
        }
    }


    @PostMapping("/edituserinproject/{projectapi}/{useruid}")
    public Object EditUser(@PathVariable String projectapi, @PathVariable String useruid, @RequestBody Editusers request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentAP = currentproject.getAuthenticationprojects();
        Projectusers currentuser = Usersfinder.GetProjectUseruid(currentAP.getUsers(), useruid);
        if (currentuser != null) {
            currentuser.setEmail(request.getEmail());
            currentuser.setFirstname(request.getFirstname());
            currentuser.setLastname(request.getLastname());
            currentuser.setParameters(request.getMapJson());
            currentuser.setUsername(request.getUsername());
            currentuser.setUsertoken(request.getUsertoken());
            currentuser.setVisuals(request.getVisuals());
            projectrepo.save(currentproject);
            Map<String, Object> response = new HashMap<>();
            response.put("useruid", currentuser.getUseruid());
            response.put("response", currentuser.getVisuals());
            return response;
        } else {
            Map<String , Object> response = new HashMap<>();
            response.put("error", "User does not exist");
            return response;
        }
    }

 @PostMapping("/setauthmethod/{projectapi}")
  public Object SetAuthMethod(@PathVariable String projectapi, @RequestBody Setauthmethod request) {
    Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
    Projectauthentication currentAP = currentproject.getAuthenticationprojects();
    currentAP.setAuthmethod(request.getAuthmethod());
    projectrepo.save(currentproject);

    List<Object> responseobject = new ArrayList<>();
    for (Projectusers docs: currentAP.getUsers()) {
        Map<String , Object> userjson = new HashMap<>();
        userjson.put("useruid", docs.getUseruid());
        userjson.put("response", docs.getVisuals());
        responseobject.add(userjson);
    }
    Map<String, Object> response = new HashMap<>();
    response.put("users", responseobject);
    response.put("authmethod", currentAP.getAuthmethod());
    response.put("labels", currentAP.getLabels());
    response.put("tracking" , currentAP.getTracking());
    response.put("trackinglabels", currentAP.getLabels().subList(5, currentAP.getLabels().size()));
    return response;
  }
}