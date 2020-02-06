package com.apigroups.api.apicontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.apigroups.api.functions.Finder;
import com.apigroups.api.functions.GeneratorId;
import com.apigroups.api.functions.tables.Tablefunctions;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.projectanalytics.Tables;
import com.apigroups.api.requests.projectanalytics.tables.Tablerequest;
import com.apigroups.api.requests.projectanalytics.tables.Tableresponse;
import com.apigroups.api.requests.projectanalytics.tables.tableusuage.Credlabels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/analytics/table")
@RestController
public class Tablecontroller {
    @Autowired
    private Projectrepo projectrepo;

    @GetMapping("/gettable/{apiKey}/{tablename}")
    public Object GetTable(@PathVariable Optional<String> apiKey, @PathVariable String tablename) {
        if (apiKey.isPresent() != true) {
         return new ResponseEntity<String>("your apiKey is still empty", HttpStatus.OK);
        } else {
        Projects currentproject = Finder.findProjectByApiKey(projectrepo.findAll() , apiKey.get());
        Map<String , Object> response = new HashMap<>();
        for (Tables docs: currentproject.getProjecttables()) {
            if (docs.getTablename().equals(tablename) == true) {
                response.put("process", true);
                response.put("tableapi", docs.getTableapi());
            }
        }
        return response;
        }

    }

    @PostMapping("/settable/{modelapi}")
    public Tableresponse SetTable(@PathVariable String modelapi, @RequestBody Tablerequest request) {
        Projects currentproject = Finder.findProjectByApiKey(projectrepo.findAll() , request.getApiKey());
        Tables currenttable = Tablefunctions.findTable(currentproject.getProjecttables(), modelapi);
        Tableresponse tableresponse = new Tableresponse();
        if (currenttable.getTablelables().size() == 0) {
            currenttable.setTablelables(request.getLabels());
            tableresponse.setReponseid(GeneratorId.GenerateId(130));
            tableresponse.setResponse(request.getRequest());
            tableresponse.setError(null);
            List<Tableresponse> responseArray = currenttable.getTabledata();
            responseArray.add(tableresponse);
            currenttable.setTabledata(responseArray);    
            projectrepo.save(currentproject); 
        } else {
            Boolean checkerResult = true;
            for (int i = 0; i < currenttable.getTablelables().size(); i++) {
                if (request.getLabels().get(i).equals(currenttable.getTablelables().get(i)) != true) {
                    checkerResult = false;
                }
            }
            if (checkerResult.equals(false) == true) {
                tableresponse.setError("The labels you sent for a table set request are not correalting to the ones you set previosuly before. Please re-send the right label array");
            } else {
                tableresponse.setReponseid(GeneratorId.GenerateId(130));
                tableresponse.setResponse(request.getRequest());
                List<Tableresponse> responseArray = currenttable.getTabledata();
                responseArray.add(tableresponse);
                currenttable.setTabledata(responseArray); 
                projectrepo.save(currentproject); 
            }
        }
        
        return tableresponse;
    }

    @GetMapping("/getwholetable/{apiKey}/{tableapi}")
    public Object GetWholeTable(@PathVariable String apiKey ,@PathVariable Optional<String> tableapi) {
        if (tableapi.isPresent() == false) {
            Map<String, Object>  response = new HashMap<>();
            response.put("status", false);
            response.put("response", "The table does not exist");
            return response;
        } else {
            Projects currentproject = Finder.findProjectByApiKey(projectrepo.findAll(), apiKey);
            List<Tables> result = currentproject.getProjecttables();
            Tables currenttables = Tablefunctions.findTable(result, tableapi.get());
            Map<String , Object> response = new HashMap<>();
            response.put("status", true);
            response.put("response", currenttables.getTabledata());
            return response;
        }
    }

    @GetMapping("/gettabledetails/{apiKey}/{tableapi}")
    public Object GetTableDetails(@PathVariable String apiKey, @PathVariable String tableapi) {
        Projects currentProject = Finder.findProjectByApiKey(projectrepo.findAll(), apiKey);
        List<Tables> allTables = currentProject.getProjecttables();
        Tables currenttable = Tablefunctions.findTable(allTables, tableapi);
        Map<String, Object> response = new HashMap<>();
        response.put("tablename", currenttable.getTablename());
        response.put("tablelabels", currenttable.getTablelables());
        response.put("tabledata", currenttable.getTabledata());
        response.put("datasize", currenttable.getTabledata().size());
        return response;
    }

    @GetMapping("/getTableData/{apiKey}/{tableapi}")
    public Tables GetTableData(@PathVariable String apiKey, @PathVariable String tableapi) {
        Projects currentproject = Finder.findProjectByApiKey(projectrepo.findAll(), apiKey);
        Tables currenttable = Tablefunctions.findTable(currentproject.getProjecttables(), tableapi);
        return currenttable;
    }

    @PostMapping("/editTableData/{apiKey}/{tableapi}/{tabledataid}")
    public Tableresponse EditTableData(@PathVariable String apiKey, @PathVariable String tableapi, @PathVariable String tabledataid, @RequestBody Object request) {
        Projects currentproject = Finder.findProjectByApiKey(projectrepo.findAll(), apiKey);
        Tables currenttable = Tablefunctions.findTable(currentproject.getProjecttables(), tableapi);
        Tableresponse currentdata = Tablefunctions.findTableData(currenttable.getTabledata(), tabledataid);
            currentdata.setResponse(request);
            projectrepo.save(currentproject);
            return currentdata;
    }
    @PostMapping("/deletetableitem/{apiKey}/{tableapi}")
    public List<Tableresponse> DeleteTableItem(@PathVariable String apiKey, @PathVariable String tableapi, @RequestBody Tableresponse request) {
        Projects currentproject = Finder.findProjectByApiKey(projectrepo.findAll(), apiKey);
        Tables currenttable = Tablefunctions.findTable(currentproject.getProjecttables(), tableapi);
        List<Tableresponse> tablelist = currenttable.getTabledata();
        List<Tableresponse> newList = new ArrayList<>();
        for (Tableresponse docs: tablelist) {
            if (docs.getReponseid().equals(request.getReponseid()) != true) {
               newList.add(docs);
            }
        }
        currenttable.setTabledata(newList);
        projectrepo.save(currentproject);
        return newList;
    }
    @PostMapping("/addentry/{apiKey}/{tableapi}")
    public Tableresponse AddEntry(@PathVariable String apiKey, @PathVariable String tableapi, @RequestBody Object request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), apiKey);
        Tables currenttable = Tablefunctions.findTable(currentproject.getProjecttables(), tableapi);
        String responseid = GeneratorId.GenerateId(170);
        List<Tableresponse> allList = currenttable.getTabledata();
        Tableresponse newentry = new Tableresponse();
        newentry.setReponseid(responseid);
        newentry.setResponse(request);
        allList.add(newentry);
        currenttable.setTabledata(allList);
        projectrepo.save(currentproject);
        return newentry;
    }

    @PostMapping("/setcredlabels/{projectapi}/{tableapi}")
    public Object SetCredLabels(@PathVariable String projectapi, @PathVariable String tableapi, @RequestBody Credlabels request) {
        Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
        Tables currentTable = Tablefunctions.findTable(currentproject.getProjecttables(), tableapi);
        currentTable.setTablelables(request.getLabels());
        projectrepo.save(currentproject);
        Map<String, Object> response = new HashMap<>();
        response.put("response", request.getLabels());
        return response;
    }
}