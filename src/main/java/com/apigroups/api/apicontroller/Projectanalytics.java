package com.apigroups.api.apicontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apigroups.api.functions.Finder;
import com.apigroups.api.functions.model.Modelfinder;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.Serviceproject;
import com.apigroups.api.requests.projectanalytics.Model;
import com.apigroups.api.requests.projectanalytics.tables.tableusuage.tableedits.Tablelabeledits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
 @RequestMapping("/projectanalytics")
 @RestController
 public class Projectanalytics {
 @Autowired
 private Projectrepo projectrepo;
 @PostMapping("/initalize")
 public Object InitModel(@RequestBody Serviceproject request) {
    Projects currentproject = Finder.findProject(projectrepo.findAll() , request.getProjectapi());
    Serviceproject currentservice = currentproject.getService();
    Map<String, Object> orginaljson = new HashMap<>();
    orginaljson.put("projectid", currentservice.getProjectid());
    orginaljson.put("projectkey", currentservice.getProjectkey());
    orginaljson.put("backendjpiid", currentservice.getBackendjpiid());
    orginaljson.put("frontendjpiid", currentservice.getFrontendjpiid());
    orginaljson.put("frontcomplianceid", currentservice.getFrontcomplianceid());
    orginaljson.put("projecttitle", currentservice.getProjecttitle());
    orginaljson.put("projectapi", currentservice.getProjectapi());
    Map<String, Object> newjson = new HashMap<>();
    newjson.put("projectid", request.getProjectid());
    newjson.put("projectkey", request.getProjectkey());
    newjson.put("backendjpiid", request.getBackendjpiid());
    newjson.put("frontendjpiid", request.getFrontendjpiid());
    newjson.put("frontcomplianceid", request.getFrontcomplianceid());
    newjson.put("projecttitle", request.getProjecttitle());
    newjson.put("projectapi", request.getProjectapi());
    Map<String, Object> response = new HashMap<>();
    if (orginaljson.equals(newjson) == true) {
      response.put("response", true);
      response.put("api", currentproject.getProjectapi());
    } else {
      response.put("response", false);
      response.put("api", null);
    }  
    return response;  
  }

  @GetMapping("/getmodels/{projectapi}")
  public List<Object> GetModels(@PathVariable String projectapi) {
    Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
    List<Model> projectModels = currentproject.getProjectmodels();
    List<Object> response = new ArrayList<>();
    for (Model docs: projectModels) {
      Map<String , Object> responsejson = new HashMap<>();
      responsejson.put("modelname", docs.getModelname());
      responsejson.put("modeldescription", docs.getModeldescription());
      responsejson.put("modelapi", docs.getModelapi());
      responsejson.put("modeltype", docs.getDatatype());
      responsejson.put("redirectapi", "/project/projectanalytics/" + projectapi + "/" + docs.getModelapi());
      response.add(responsejson);
    }
    return response;
  }

  @PostMapping("/setModelName/{projectapi}/{modelapi}")
  public Object setModelName(@PathVariable String projectapi, @PathVariable String modelapi, @RequestBody Tablelabeledits request) {
    Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
    Model currentmodel = Modelfinder.GetModel(currentproject.getProjectmodels(), modelapi);
    currentmodel.setModelname(request.getTitle());
    currentmodel.setModeldescription(request.getDescription());
    projectrepo.save(currentproject);
    Map<String , Object> response = new HashMap<>();
    response.put("modelname", request.getTitle());
    response.put("modeldescription", request.getDescription());
    response.put("modelapi", currentmodel.getModelapi());
    response.put("modeltype", currentmodel.getDatatype());
    response.put("redirectapi", "/project/projectanalytics/" + projectapi + "/" + modelapi);
    return response;
  }

}