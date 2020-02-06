package com.apigroups.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.apigroups.api.functions.GeneratorId;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.repo.Userrepo;
import com.apigroups.api.requests.Pendingrequests;
import com.apigroups.api.requests.Projectnotes;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.Users;
import com.apigroups.api.requests.authentication.Projectauthentication;
import com.apigroups.api.requests.authentication.users.Projectusers;
import com.apigroups.api.requests.projectanalytics.Bargraphs;
import com.apigroups.api.requests.projectanalytics.Linegraphs;
import com.apigroups.api.requests.projectanalytics.Model;
import com.apigroups.api.requests.projectanalytics.Tables;
import com.apigroups.api.requests.projectrequests.Frontendprojectcreds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/dash")
@RestController
public class Dashcontroller {
    @Autowired
    private Userrepo userrepo;
    @Autowired
    private Projectrepo projectrepo;

    public Projects findProject(String api) {
        for (Projects doc : projectrepo.findAll()) {
          if (doc.getProjectapi().equals(api) == true) {
            return doc;
          }
        }
        return null;
      }


    @GetMapping("/getusers")
    public List<Object> GetUsers() {
        List<Users> allUsers = userrepo.findAll();
        List<Object> response = new ArrayList<>();
        for (Users doc: allUsers) {
          Map<String, Object> responsejson = new HashMap<>();
          responsejson.put("firstname", doc.getFirstname());
          responsejson.put("lastname", doc.getLastname());
          responsejson.put("email", doc.getEmail());
          responsejson.put("userid", doc.getId());
          responsejson.put("username", doc.getUsername());
          responsejson.put("fullname", doc.getFirstname() + " " + doc.getLastname());
          response.add(responsejson);
        }
        return response;
    }

    @PostMapping("/createproject/{useruid}")
    public Map<String, Object> CreateProject(@PathVariable String useruid, @RequestBody Projects request) {
        Optional<Users> creator = userrepo.findById(useruid);
        String projectapi = GeneratorId.GenerateId(150);
        String projectid = GeneratorId.GenerateId(200);
        List<String> projectusers = new ArrayList<>();
        Projects newproject = new Projects();
        newproject.setProjectname(request.getProjectname());
        newproject.setProjectdescription(request.getProjectdescription());
        projectusers.add(useruid);
        newproject.setProjectusers(projectusers);
        newproject.setProjectid(projectid);
        newproject.setProjectapi(projectapi);
        List<String> requestedusersarray = request.getRequestedusers();
        for (String docs: requestedusersarray) {
            Optional<Users> currentuser = userrepo.findById(docs);
            if(currentuser.isPresent() == true) {
                Pendingrequests pendingrequests = new Pendingrequests();
                pendingrequests.setProjectname(request.getProjectname());
                pendingrequests.setProjectapi(projectapi);
                pendingrequests.setProjectdescription(request.getProjectdescription());
                List<Pendingrequests> pendingarray = currentuser.get().getPendingrequests();
                pendingarray.add(pendingrequests);
                currentuser.get().setPendingrequests(pendingarray);
                userrepo.save(currentuser.get());
            }
        }
        creator.get().getProjects().add(newproject);
        userrepo.save(creator.get());
        List<Projectnotes> projectnotes = new ArrayList<>();
        newproject.setProjectnotes(projectnotes);
        newproject.setProjectusers(projectusers);
        List<String> projectadmins = new ArrayList<>();
        projectadmins.add(useruid);
        newproject.setProjectadmins(projectadmins);
        newproject.setProjectkey(GeneratorId.GenerateId(250));
        newproject.setCreator(useruid);
        List<Model> newmodellist = new ArrayList<>();
        List<Tables> newtablelist = new ArrayList<>();
        List<Bargraphs> newbargraphlist = new ArrayList<>();
        List<Linegraphs> newlinegraphlist = new ArrayList<>();
        String projectservice = request.getProjectname().replace(" ", "-").trim() + GeneratorId.GenerateId(50);
        newproject.setProjectservicetitle(projectservice);
        newproject.setProjectmodels(newmodellist);
        newproject.setProjecttables(newtablelist);
        newproject.setProjectlinegraphs(newlinegraphlist);
        newproject.setProjectbargraphs(newbargraphlist);
        Projectauthentication newauthenticationsetup = new Projectauthentication();
        newauthenticationsetup.setProjectapi(projectapi);
        List<Projectusers> emptyprojectusers = new ArrayList<>();
        newauthenticationsetup.setAmountOfUsers(emptyprojectusers.size());
        newauthenticationsetup.setProjectauthid(GeneratorId.GenerateId(200));
        newauthenticationsetup.setUsers(emptyprojectusers);
        Map<String, Object> trackingJson = new HashMap<>();
        newauthenticationsetup.setTracking(trackingJson);
        List<String> labelStrings = new ArrayList<>();
        labelStrings.add("userid");
        labelStrings.add("email");
        labelStrings.add("firstname");
        labelStrings.add("lastname");
        labelStrings.add("username");
        newauthenticationsetup.setLabels(labelStrings);
        newauthenticationsetup.setAuthmethod("");
        newproject.setAuthenticationprojects(newauthenticationsetup);
        Frontendprojectcreds generateCreds = new Frontendprojectcreds();
        generateCreds.setFrontendprojectapi(GeneratorId.GenerateId(102));
        generateCreds.setProjectname(request.getProjectname());
        newproject.setFrontendcreds(generateCreds);
        projectrepo.save(newproject);
        Map<String, Object> res = new HashMap<>();
        res.put("projectapi" , projectapi);
        res.put("projectname", request.getProjectname());
        res.put("projectdescription", request.getProjectdescription());
        return res;
    }

    @GetMapping("/getprojects/{useruid}")
    public List<Object> GetProjects(@PathVariable String useruid) {
      Optional<Users> currentuser = userrepo.findById(useruid);
      List<Projects> userprojects = currentuser.get().getProjects();
      List<Object> response = new ArrayList<>();
      for (Projects docs: userprojects) {
          Map<String, Object> resjson = new HashMap<>();
          resjson.put("projectapi", docs.getProjectapi());
          resjson.put("projectname", docs.getProjectname());
          resjson.put("projectdescription", docs.getProjectdescription());
          response.add(resjson);
      }
      return response; 
    }

    @GetMapping("/getpendingrequest/{userid}")
    public Object GetPendingRequests(@PathVariable String userid) {
        Optional<Users> currentuser = userrepo.findById(userid);
        if (currentuser.isPresent() == true) {
            return currentuser.get().getPendingrequests();
        } else {
            return null;
        }
    }

    @GetMapping("/connectproject/{api}/{userid}")
    public Object ConnectProject(@PathVariable String api , @PathVariable String userid) {
        Projects currentproject = findProject(api);
        Optional<Users> currentuser = userrepo.findById(userid);
        if (currentuser.isPresent() == true) {
            List<Pendingrequests> userpens = currentuser.get().getPendingrequests();
            Predicate<Pendingrequests> byApi = object -> object.getProjectapi().equals(api) == true;
            List<Pendingrequests> updatedpens = userpens.stream().filter(byApi).collect(Collectors.toList());
            currentuser.get().setPendingrequests(updatedpens);
            List<Projects> userprojects = currentuser.get().getProjects();
            userprojects.add(currentproject);
            currentuser.get().setProjects(userprojects);
            userrepo.save(currentuser.get());
            List<String> projectusers =  currentproject.getProjectusers();
            projectusers.add(userid);
            currentproject.setProjectusers(projectusers);
            projectrepo.save(currentproject);
            Map<String, Object> response = new HashMap<>();
            response.put("projectapi", currentproject.getProjectapi());
            response.put("projectname", currentproject.getProjectname());
            response.put("projectdescription", currentproject.getProjectdescription());
            return response;
        }
        return null;
    }

   
}