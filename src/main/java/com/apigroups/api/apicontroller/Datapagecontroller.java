package com.apigroups.api.apicontroller;

import com.apigroups.api.functions.Finder;
import com.apigroups.api.functions.model.Modelfinder;
import com.apigroups.api.functions.tables.Tablefunctions;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.projectanalytics.Model;
import com.apigroups.api.requests.projectanalytics.Tables;
import com.apigroups.api.requests.projectanalytics.datapages.Tablepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/data")
@RestController
public class Datapagecontroller {
    @Autowired
    private Projectrepo projectrepo;
    @GetMapping("/gettablepagedata/{apikey}/{modelapi}")
    public Tablepage GetTablePageData(@PathVariable String apikey, @PathVariable String modelapi) {
      Projects currentproject = Finder.findProject(projectrepo.findAll(), apikey);
      Model currentmodel = Modelfinder.GetModel(currentproject.getProjectmodels(), modelapi);
      Tablepage response = new Tablepage();
      response.setDatadevs(currentmodel.getDatadevs());
      response.setDataviewers(currentmodel.getDataviewers());
      response.setTableapi(modelapi);
      Tables currenttable = Tablefunctions.findTable(currentproject.getProjecttables(), modelapi);
      response.setTabledata(currenttable.getTabledata());
      response.setTablename(currentmodel.getModelname());
      response.setTabledescription(currentmodel.getModeldescription());
      response.setProjectname(currentproject.getProjectname());
      response.setTablelabels(currenttable.getTablelables());
      return response;
    }
}