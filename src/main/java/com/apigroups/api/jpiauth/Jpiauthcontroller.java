package com.apigroups.api.jpiauth;

import java.util.List;

import com.apigroups.api.functions.Finder;
import com.apigroups.api.functions.GeneratorId;
import com.apigroups.api.functions.jpiauth.Usersfinder;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.authentication.Projectauthentication;
import com.apigroups.api.requests.authentication.users.Authenticatedlogin;
import com.apigroups.api.requests.authentication.users.Loggedout;
import com.apigroups.api.requests.authentication.users.Projectusers;
import com.apigroups.api.requests.authentication.users.loginforms.Emailandpass;
import com.apigroups.api.requests.authentication.users.loginforms.Usernameandpass;
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
@RequestMapping("/jpiauth")
@RestController
public class Jpiauthcontroller {
    @Autowired
    private Projectrepo projectrepo;
    @PostMapping("/register/registeremailandpassword/{projectapi}")
    public Authenticatedlogin RegisterUserEmailandPassword(@PathVariable String projectapi, @RequestBody Register request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentAuthProject = currentproject.getAuthenticationprojects();
        List<Projectusers> allProjectUsers = currentAuthProject.getUsers();
        Projectusers requestedusers = Usersfinder.GetProjectEmailandPassUser(allProjectUsers, request.getEmail(), request.getPassword());
        if (currentAuthProject.getAuthmethod().equals("") == true) {
            currentAuthProject.setAuthmethod("Email and Password");
        }
        if (request.getMapNames().size() > 0 && currentAuthProject.getLabels().size() == 5) {
            List<String> currentlabels = currentAuthProject.getLabels();
            for (String docs: request.getMapNames()) {
                currentlabels.add(docs);
            }
            currentAuthProject.setLabels(currentlabels);
        }
        if (requestedusers == null) {
            String useruid = GeneratorId.GenerateId(60);
            Projectusers createuser = new Projectusers();
            createuser.setAuthenticated(false);
            createuser.setEmail(request.getEmail());
            createuser.setPassword(request.getPassword());
            createuser.setParameters(request.getMapJson());
            createuser.setFirstname(request.getFirstname());
            createuser.setLastname(request.getLastname());
            createuser.setUsername(request.getUsername());
            createuser.setUseruid(useruid);
            createuser.setUsertoken(request.getUsertoken());
            createuser.setVisuals(request.getVisuals());
            allProjectUsers.add(createuser);
            currentAuthProject.setAmountOfUsers(currentAuthProject.getAmountOfUsers() + 1);
            projectrepo.save(currentproject);
            Authenticatedlogin response = new Authenticatedlogin();
            response.setAppJson(request.getMapJson());
            response.setEmail(request.getEmail());
            response.setFirstname(request.getFirstname());
            response.setLastname(request.getLastname());
            response.setUserid(useruid);
            response.setUsername(request.getUsername());
            response.setExists(true);
            return response; 
        } else {
            Authenticatedlogin response = new Authenticatedlogin();
            response.setExists(false);
            return response;
        }
    }
    @PostMapping("/login/signinemailandpassword/{projectapi}")
    public Authenticatedlogin LoginUserEmailandPassword(@RequestBody Emailandpass request, @PathVariable String projectapi) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentAuthProject = currentproject.getAuthenticationprojects();
        Projectusers currentuser = Usersfinder.GetProjectEmailandPassUser(currentAuthProject.getUsers(), request.getEmail(), request.getPassword());
        Authenticatedlogin newLoginRequest = new Authenticatedlogin();
        if (currentuser != null) {
         currentuser.setAuthenticated(true);
         projectrepo.save(currentproject);
         newLoginRequest.setAppJson(currentuser.getParameters());
         newLoginRequest.setEmail(currentuser.getEmail());
         newLoginRequest.setFirstname(currentuser.getFirstname());
         newLoginRequest.setLastname(currentuser.getLastname());
         newLoginRequest.setUserid(currentuser.getUseruid());
         newLoginRequest.setUsername(currentuser.getUsername());
         newLoginRequest.setExists(true);
        } else {
          newLoginRequest.setExists(false);
        }
        return newLoginRequest;
    }
    @PostMapping("/register/registerusernameandpassword/{projectapi}")
    public Authenticatedlogin RegisterUsernameandPassword(@PathVariable String projectapi, @RequestBody Register request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentProjectAuth = currentproject.getAuthenticationprojects();
        List<Projectusers> allProjectUsers = currentProjectAuth.getUsers();
        Projectusers checkUsers = Usersfinder.GetProjectUsernameandPassUser(allProjectUsers, request.getUsername(), request.getPassword());
        if (currentProjectAuth.getAuthmethod().equals("") == true) {
            currentProjectAuth.setAuthmethod("Username and Password");
        }
        if (request.getMapNames().size() > 0 && currentProjectAuth.getLabels().size() == 5) {
            List<String> currentlabels = currentProjectAuth.getLabels();
            for (String docs: request.getMapNames()) {
                currentlabels.add(docs);
            }
            currentProjectAuth.setLabels(currentlabels);
        }
        if (checkUsers == null) {
            currentProjectAuth.setAmountOfUsers(currentProjectAuth.getAmountOfUsers() + 1);
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
            Authenticatedlogin newauth = new Authenticatedlogin();
            newauth.setAppJson(request.getMapJson());
            newauth.setEmail(request.getEmail());
            newauth.setFirstname(request.getFirstname());
            newauth.setLastname(request.getLastname());
            newauth.setUserid(userid);
            newauth.setUsername(request.getUsername());
            newauth.setExists(true);
            return newauth;
        } else {
            Authenticatedlogin newauth = new Authenticatedlogin();
            newauth.setExists(false);
            return newauth;
        }
    }

    @PostMapping("/login/loginusernameandpassword/{projectapi}")
    public Authenticatedlogin LoginUserUsernameandPassword(@PathVariable String projectapi,  @RequestBody Usernameandpass request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication authProject = currentproject.getAuthenticationprojects();
        Projectusers currentUser = Usersfinder.GetProjectUsernameandPassUser(authProject.getUsers(), request.getUsername(), request.getPassword());
        if (currentUser != null) {
            currentUser.setAuthenticated(true);
            projectrepo.save(currentproject);
            Authenticatedlogin newloginrequest = new Authenticatedlogin();
            newloginrequest.setAppJson(currentUser.getParameters());
            newloginrequest.setEmail(currentUser.getEmail());
            newloginrequest.setExists(true);
            newloginrequest.setFirstname(currentUser.getFirstname());
            newloginrequest.setLastname(currentUser.getLastname());
            newloginrequest.setUserid(currentUser.getUseruid());
            newloginrequest.setUsername(currentUser.getUsername());
            return newloginrequest;
        } else {
            Authenticatedlogin newloginrequest = new Authenticatedlogin();
            newloginrequest.setExists(false);
            return newloginrequest;
        }
    }
    @GetMapping("/logout/{projectapi}/{useruid}")
    public Loggedout LogoutProjectUser(@PathVariable String projectapi, @PathVariable String useruid) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Projectauthentication currentAuthProject = currentproject.getAuthenticationprojects();
        Projectusers currentuser = Usersfinder.GetProjectUseruid(currentAuthProject.getUsers(), useruid);
        if (currentuser != null) {
            System.out.println("correct: " +  currentuser.getUseruid());
            currentuser.setAuthenticated(false);
            projectrepo.save(currentproject);
            Loggedout response = new Loggedout();
            response.setStatus(true);
            response.setUseruid(useruid);
            return response;
        } else {
            System.out.println("uncorrect not working");
            Loggedout response = new Loggedout();
            response.setStatus(false);
            response.setUseruid(useruid);
            return response;
        }
    }
}