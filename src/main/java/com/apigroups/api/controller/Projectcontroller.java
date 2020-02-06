package com.apigroups.api.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.apigroups.api.functions.Finder;
import com.apigroups.api.functions.GeneratorId;
import com.apigroups.api.repo.Projectrepo;
import com.apigroups.api.repo.Userrepo;
import com.apigroups.api.requests.Projectnotes;
import com.apigroups.api.requests.Projects;
import com.apigroups.api.requests.Serviceproject;
import com.apigroups.api.requests.Users;
import com.apigroups.api.requests.projectanalytics.Bargraphs;
import com.apigroups.api.requests.projectanalytics.Linegraphs;
import com.apigroups.api.requests.projectanalytics.Model;
import com.apigroups.api.requests.projectanalytics.Tables;
import com.apigroups.api.requests.projectanalytics.tables.Tableresponse;
import com.apigroups.api.requests.projectnotes.Comments;
import com.apigroups.api.requests.projectnotes.Editedcode;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/project")
@RestController
public class Projectcontroller {
  @Autowired
  private Projectrepo projectrepo;
  @Autowired
  private Userrepo userrepo;

  @GetMapping("/getprojectcreds/{projectapi}")
  public String GetCreds(@PathVariable String projectapi) {
    return Finder.findProject( projectrepo.findAll(), projectapi).getProjectname();
  }

  // Notes Section start
  @PostMapping("/createnote/{api}")
  public Object CreateNote(@PathVariable String api, @RequestBody Projectnotes projectnotes) {
    List<Projects> currentproject = projectrepo.findByProjectapi(api);
    if (currentproject.size() == 1) {
      String noteid = GeneratorId.GenerateId(80);
      List<Comments> comments = new ArrayList<>();
      Projectnotes currentnotes = new Projectnotes();
      currentnotes.setNote(projectnotes.getNote());
      currentnotes.setModel(projectnotes.getModel());
      currentnotes.setComments(comments);
      currentnotes.setDisplaydate(projectnotes.getDisplaydate());
      currentnotes.setCreator(projectnotes.getCreator());
      currentnotes.setNoteid(noteid);
      List<Editedcode> editsmade = new ArrayList<>();
      currentnotes.setEditsmade(editsmade);
      Projects projectnow = currentproject.get(0);
      List<Projectnotes> projectnotes2 = projectnow.getProjectnotes();
      List<Model> emptymodel = new ArrayList<>();
      projectnotes2.add(currentnotes);
      projectnow.setProjectnotes(projectnotes2);
      projectnow.setProjectmodels(emptymodel);
      projectrepo.save(projectnow);
      Map<String, Object> responsejson = new HashMap<>();
      responsejson.put("note", projectnotes.getNote());
      responsejson.put("model", projectnotes.getModel());
      responsejson.put("creator", projectnotes.getCreator());
      responsejson.put("displaydate", projectnotes.getDisplaydate());
      responsejson.put("comments", comments);
      responsejson.put("noteid", noteid);
      responsejson.put("editedcode", projectnotes.getEditsmade());
      return responsejson;
    }
    return null;
  }

  @GetMapping("/getprojectnotes/{api}")
  public List<Projectnotes> GetNotes(@PathVariable String api) {
    return Finder.findProject(projectrepo.findAll() , api).getProjectnotes();
  }



  @PostMapping("/commentonnotes/{api}/{noteid}")
  public Object CommentOnNotes(@PathVariable String api, @PathVariable String noteid, @RequestBody Comments request) {
    Projects currentproject = Finder.findProject(projectrepo.findAll() , api);
    Projectnotes currentnote = Finder.findProjectNotes(currentproject.getProjectnotes(), noteid);
    Comments comment = new Comments();
    comment.setCommentid(GeneratorId.GenerateId(160));
    comment.setMessage(request.getMessage());
    comment.setUsername(request.getUsername());
    List<Comments> commentarray = currentnote.getComments();
    commentarray.add(comment);
    currentnote.setComments(commentarray);
    projectrepo.save(currentproject);
    Map<String, Object> response = new HashMap<>();
    response.put("username", request.getUsername());
    response.put("message", request.getMessage());
    return response;
  }

  @PostMapping("/addedits/{api}/{noteid}")
  public Editedcode AddEdits(@PathVariable String api, @PathVariable String noteid, @RequestBody Editedcode request) {
    Projects currentproject = Finder.findProject(projectrepo.findAll(), api);
    Projectnotes currentnote = Finder.findProjectNotes(currentproject.getProjectnotes(), noteid);
    List<Editedcode> listEditCode = currentnote.getEditsmade();
    Editedcode newEdit = new Editedcode();
    newEdit.setAuthor(request.getAuthor());
    newEdit.setEditid(GeneratorId.GenerateId(175));
    newEdit.setModel(request.getModel());
    listEditCode.add(newEdit);
    projectrepo.save(currentproject);
    return newEdit;
  }

  //This is the project users sections
  @GetMapping("/getuser/{api}")
  public Object GetProjectUser(@PathVariable String api) {
    Projects currentproject = Finder.findProject(projectrepo.findAll() , api);
    List<Object> response = new ArrayList<>();
    for (String docs : currentproject.getProjectusers()) {
      Optional<Users> currentuser = userrepo.findById(docs);
      if (currentuser.isPresent() == true) {
        Map<String, Object> userjson = new HashMap<>();
        userjson.put("username", currentuser.get().getUsername());
        userjson.put("firstname", currentuser.get().getFirstname());
        userjson.put("lastname", currentuser.get().getLastname());
        userjson.put("email", currentuser.get().getEmail());
        userjson.put("userid", currentuser.get().getId());
        response.add(userjson);
      }
    }
    return response;
  }

  @GetMapping("/getservicecredentials/{api}")
  public ResponseEntity<ByteArrayResource> GetServiceCredentials(@PathVariable String api) throws IOException {
      Projects currentproject = Finder.findProject(projectrepo.findAll() , api);
      String backendjpi = GeneratorId.GenerateId(150);
      String frontendjpi = GeneratorId.GenerateId(151);
      String frontcomplianceid = GeneratorId.GenerateId(152);
      String apikey = GeneratorId.GenerateId(800);
      File newservicefile = new File(currentproject.getProjectservicetitle() + ".json");
      Serviceproject newservice = new Serviceproject();
      newservice.setBackendjpiid(backendjpi);
      newservice.setFrontendjpiid(frontendjpi);
      newservice.setFrontcomplianceid(frontcomplianceid);
      newservice.setProjectapi(api);
      newservice.setProjectid(currentproject.getProjectid());
      newservice.setProjectkey(currentproject.getProjectkey());
      newservice.setProjecttitle(currentproject.getProjectservicetitle());
      newservice.setApikey(apikey);
      currentproject.setService(newservice);
      projectrepo.save(currentproject);
      Map<String , Object> servicejson = new HashMap<>();
      servicejson.put("projectid", currentproject.getProjectid());
      servicejson.put("projectkey", currentproject.getProjectkey());
      servicejson.put("backendjpiid" , backendjpi);
      servicejson.put("frontendjpiid", frontendjpi);
      servicejson.put("frontcomplianceid" , frontcomplianceid);
      servicejson.put("projecttitle", currentproject.getProjectservicetitle());
      servicejson.put("projectapi", currentproject.getProjectapi());
      JSONObject jsonObject = new JSONObject(servicejson);
      if (newservicefile.createNewFile() == true) {
      FileWriter filewriter = new FileWriter(newservicefile);
      filewriter.write(jsonObject.toJSONString());
      filewriter.close();
      org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        Path path = Paths.get(newservicefile.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok().headers(headers).contentLength(newservicefile.length()).contentType(MediaType.parseMediaType("application/json")).body(resource);
      }
      return null;
  }

  @GetMapping("/getservicetitle/{api}")
  public String GetServiceTitle(@PathVariable String api) {
    Projects currentproject = Finder.findProject(projectrepo.findAll() , api);
    return currentproject.getProjectservicetitle();
  }

  @GetMapping("/getprojectusers/{api}")
  public List<Object> GetProjectUsers(@PathVariable String api) {
    Projects currentproject = Finder.findProject(projectrepo.findAll() , api);
    List<Object> getProjectUsers = new ArrayList<>();
    for (String docs: currentproject.getProjectusers()) {
      Map<String, Object> usercreds = new HashMap<>();
      Optional<Users> currentuser = userrepo.findById(docs);
      if (currentuser.isPresent() == true) {
        usercreds.put("username", currentuser.get().getUsername());
        usercreds.put("fullname", currentuser.get().getFirstname() + " " + currentuser.get().getLastname());
        usercreds.put("userid", currentuser.get().getId());
        getProjectUsers.add(usercreds);
      }
    }
     return getProjectUsers;
  }

  @PostMapping("/createmodel/{api}")
  public Object CreateModel(@PathVariable String api, @RequestBody Model request) {
    Projects currentproject = Finder.findProject(projectrepo.findAll() , api);
    List<Bargraphs> currentbargraphs = currentproject.getProjectbargraphs();
    List<Linegraphs> currentlinegraphs = currentproject.getProjectlinegraphs();
    List<Tables> currenttables = currentproject.getProjecttables();
    String modelapi = GeneratorId.GenerateId(55);
    List<Object> currentdataarray = new ArrayList<>();
    if (request.getDatatype().equals("table") == true) {
      List<Tables> allTables = currentproject.getProjecttables();
      for (Tables doc : allTables) {
        if (doc.getTablename().equals(request.getModelname()) == true) {
          return new ResponseEntity<String>("Error: Name of Table Exists" , HttpStatus.OK);
        }
      }
      List<Tableresponse> tabledata = new ArrayList<>();
      List<String> tablellables = new ArrayList<>();
      Tables tables = new Tables();
      tables.setTabledata(tabledata);
      tables.setTabledescription(request.getModeldescription());
      tables.setTabledevs(request.getDatadevs());
      tables.setTableviewers(request.getDataviewers());
      tables.setTablename(request.getModelname());
      tables.setTableid(GeneratorId.GenerateId(120));
      tables.setTableapi(modelapi);
      tables.setTablelables(tablellables);
      currenttables.add(tables);
      currentproject.setProjecttables(currenttables);
    } else if (request.getDatatype().equals("bar") == true) {
      List<Bargraphs> allBargraphs = currentproject.getProjectbargraphs();
      for (Bargraphs docs: allBargraphs) {
        if (docs.getBargraphname().equals(request.getModelname()) == true) {
          return new ResponseEntity<String>("Error: Name of Bargraph Exists" , HttpStatus.OK);
        }
      }
      Bargraphs bargraphs = new Bargraphs();
      bargraphs.setBardata(currentdataarray);
      bargraphs.setBardescription(request.getModeldescription());
      bargraphs.setBargraphdevs(request.getDatadevs());
      bargraphs.setBarviewers(request.getDataviewers());
      bargraphs.setBargraphname(request.getModelname());
      bargraphs.setBargraphid(GeneratorId.GenerateId(120));
      currentbargraphs.add(bargraphs);
      currentproject.setProjectbargraphs(currentbargraphs);
    } else if (request.getDatatype().equals("line") == true) {
      List<Linegraphs> allLinegraphs = currentproject.getProjectlinegraphs();
      for (Linegraphs docs: allLinegraphs) {
        if (docs.getLinegraphname().equals(request.getModelname()) == true) {
          return new ResponseEntity<String>("Error: Name of Line graph Exists" , HttpStatus.OK);
        }
      }
      Linegraphs linegraphs = new Linegraphs();
      linegraphs.setLinedata(currentdataarray);
      linegraphs.setLinedescriptions(request.getModeldescription());
      linegraphs.setLinedevs(request.getDatadevs());
      linegraphs.setLineviewers(request.getDataviewers());
      linegraphs.setLinegraphname(request.getModelname());
      linegraphs.setLineid(GeneratorId.GenerateId(120));
      currentlinegraphs.add(linegraphs);
      currentproject.setProjectlinegraphs(currentlinegraphs);
    } else {
      return new ResponseEntity<String>("Error: you have not chosen a data type" , HttpStatus.OK);
    }
    Model newmodel = new Model();
    newmodel.setModelname(request.getModelname());
    newmodel.setDatadevs(request.getDatadevs());
    newmodel.setDatatype(request.getDatatype());
    newmodel.setDataviewers(request.getDataviewers());
    newmodel.setModeldescription(request.getModeldescription());
    newmodel.setModelid(GeneratorId.GenerateId(180));
    newmodel.setModelapi(modelapi);
    List<Model> currentmodellist = currentproject.getProjectmodels();
    currentmodellist.add(newmodel);
    currentproject.setProjectmodels(currentmodellist);
    projectrepo.save(currentproject);
    List<Object> datadevs = new ArrayList<>();
    for (String docs: request.getDatadevs()) {
      Optional<Users> currentusers = userrepo.findById(docs);
      if (currentusers.isPresent() == true) {
        Map<String, Object> usercreds = new HashMap<>();
        usercreds.put("firstname", currentusers.get().getFirstname());
        usercreds.put("lastname", currentusers.get().getLastname());
        usercreds.put("username", currentusers.get().getUsername());
        usercreds.put("email", currentusers.get().getEmail());
        usercreds.put("userid", currentusers.get().getId());
        datadevs.add(usercreds);
      }
     }
     List<Object> dataviewers = new ArrayList<>();
     for (String docs: request.getDataviewers()) {
       Optional<Users> currentuser = userrepo.findById(docs);
       if (currentuser.isPresent() == true) {
         Map<String , Object> usercreds = new HashMap<>();
         usercreds.put("firstname", currentuser.get().getFirstname());
         usercreds.put("lastname", currentuser.get().getLastname());
         usercreds.put("username", currentuser.get().getUsername());
         usercreds.put("email", currentuser.get().getEmail());
         usercreds.put("userid", currentuser.get().getId());
         dataviewers.add(usercreds);
       }
     }
    Map<String, Object> response = new HashMap<>();
    response.put("modelname", request.getModelname());
    response.put("modeldescription", request.getModeldescription());
    response.put("modelapi", modelapi);
    response.put("modeltype", request.getDatatype());
    response.put("redirectapi", "/project/projectanalytics/" + api + "/" + modelapi);
    return response;
  }

  @GetMapping("/getfrontendapi/{projectapi}")
  public Object GetFrontEndApi(@PathVariable String projectapi) {
    Projects currentproject = Finder.findProject(projectrepo.findAll(), projectapi);
    return currentproject.getFrontendcreds();
  }

}

