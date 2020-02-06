package com.apigroups.api.requests;

import java.util.List;

import com.apigroups.api.requests.authentication.Projectauthentication;
import com.apigroups.api.requests.projectanalytics.Bargraphs;
import com.apigroups.api.requests.projectanalytics.Linegraphs;
import com.apigroups.api.requests.projectanalytics.Model;
import com.apigroups.api.requests.projectanalytics.Tables;
import com.apigroups.api.requests.projectrequests.Frontendprojectcreds;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Projects {
    @Id
    private String id;
    private String projectname;
    private String projectdescription;
    private String projectid;
    private String projectapi;
    private List<String> projectusers;
    private List<String> requestedusers;
    private List<String> projectadmins;
    private String projectkey;
    private List<Projectapi> projectapis;
    private List<Projectnotes> projectnotes;
    private String creator;
    private Serviceproject service;
    private String projectservicetitle;
    private List<Model> projectmodels;
    private List<Bargraphs> projectbargraphs;
    private List<Linegraphs> projectlinegraphs;
    private List<Tables> projecttables;
    private Projectauthentication authenticationprojects;
    private Frontendprojectcreds frontendcreds;
    
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectdescription() {
        return projectdescription;
    }

    public void setProjectdescription(String projectdescription) {
        this.projectdescription = projectdescription;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectapi() {
        return projectapi;
    }

    public void setProjectapi(String projectapi) {
        this.projectapi = projectapi;
    }

    public List<String> getProjectusers() {
        return projectusers;
    }

    public void setProjectusers(List<String> projectusers) {
        this.projectusers = projectusers;
    }

    public List<String> getRequestedusers() {
        return requestedusers;
    }

    public void setRequestedusers(List<String> requestedusers) {
        this.requestedusers = requestedusers;
    }

    public List<String> getProjectadmins() {
        return projectadmins;
    }

    public void setProjectadmins(List<String> projectadmins) {
        this.projectadmins = projectadmins;
    }

    public String getProjectkey() {
        return projectkey;
    }

    public void setProjectkey(String projectkey) {
        this.projectkey = projectkey;
    }

    public List<Projectapi> getProjectapis() {
        return projectapis;
    }

    public void setProjectapis(List<Projectapi> projectapis) {
        this.projectapis = projectapis;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Projectnotes> getProjectnotes() {
        return projectnotes;
    }

    public void setProjectnotes(List<Projectnotes> projectnotes) {
        this.projectnotes = projectnotes;
    }

    public Serviceproject getService() {
        return service;
    }

    public void setService(Serviceproject service) {
        this.service = service;
    }

    public String getProjectservicetitle() {
        return projectservicetitle;
    }

    public void setProjectservicetitle(String projectservicetitle) {
        this.projectservicetitle = projectservicetitle;
    }

    public List<Model> getProjectmodels() {
        return projectmodels;
    }

    public void setProjectmodels(List<Model> projectmodels) {
        this.projectmodels = projectmodels;
    }

    public List<Bargraphs> getProjectbargraphs() {
        return projectbargraphs;
    }

    public void setProjectbargraphs(List<Bargraphs> projectbargraphs) {
        this.projectbargraphs = projectbargraphs;
    }

    public List<Linegraphs> getProjectlinegraphs() {
        return projectlinegraphs;
    }

    public void setProjectlinegraphs(List<Linegraphs> projectlinegraphs) {
        this.projectlinegraphs = projectlinegraphs;
    }

    public List<Tables> getProjecttables() {
        return projecttables;
    }

    public void setProjecttables(List<Tables> projecttables) {
        this.projecttables = projecttables;
    }

    public Projectauthentication getAuthenticationprojects() {
        return authenticationprojects;
    }

    public void setAuthenticationprojects(Projectauthentication authenticationprojects) {
        this.authenticationprojects = authenticationprojects;
    }

    public Frontendprojectcreds getFrontendcreds() {
        return frontendcreds;
    }

    public void setFrontendcreds(Frontendprojectcreds frontendcreds) {
        this.frontendcreds = frontendcreds;
    }
}