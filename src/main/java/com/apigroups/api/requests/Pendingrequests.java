package com.apigroups.api.requests;

import org.springframework.data.annotation.Id;

public class Pendingrequests {
    @Id
    private String id;
    private String projectname;
    private String projectapi;
    private String projectdescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectapi() {
        return projectapi;
    }

    public void setProjectapi(String projectapi) {
        this.projectapi = projectapi;
    }

    public String getProjectdescription() {
        return projectdescription;
    }

    public void setProjectdescription(String projectdescription) {
        this.projectdescription = projectdescription;
    }
}