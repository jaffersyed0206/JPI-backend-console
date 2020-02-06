package com.apigroups.api.requests;

import java.util.List;

public class Projectapi {
    private String apiname;
    private String apidescription;
    private String apimodel;
    private String install;
    private List<String> projecttags;
    private String apiid;

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    public String getApidescription() {
        return apidescription;
    }

    public void setApidescription(String apidescription) {
        this.apidescription = apidescription;
    }

    public String getApimodel() {
        return apimodel;
    }

    public void setApimodel(String apimodel) {
        this.apimodel = apimodel;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }


    public String getApiid() {
        return apiid;
    }

    public void setApiid(String apiid) {
        this.apiid = apiid;
    }

    public List<String> getProjecttags() {
        return projecttags;
    }

    public void setProjecttags(List<String> projecttags) {
        this.projecttags = projecttags;
    }

}