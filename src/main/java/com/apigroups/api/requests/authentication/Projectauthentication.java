package com.apigroups.api.requests.authentication;

import java.util.List;

import com.apigroups.api.requests.authentication.users.Projectusers;

public class Projectauthentication {
    private String projectapi;
    private String projectauthid;
    private int amountOfUsers;
    private List<Projectusers> users;
    private String authmethod;
    private List<String> labels;
    private Object tracking;

    public String getProjectapi() {
        return projectapi;
    }

    public void setProjectapi(String projectapi) {
        this.projectapi = projectapi;
    }

    public String getProjectauthid() {
        return projectauthid;
    }

    public void setProjectauthid(String projectauthid) {
        this.projectauthid = projectauthid;
    }

    public int getAmountOfUsers() {
        return amountOfUsers;
    }

    public void setAmountOfUsers(int amountOfUsers) {
        this.amountOfUsers = amountOfUsers;
    }

    public List<Projectusers> getUsers() {
        return users;
    }

    public void setUsers(List<Projectusers> users) {
        this.users = users;
    }


    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Object getTracking() {
        return tracking;
    }

    public void setTracking(Object tracking) {
        this.tracking = tracking;
    }

    public String getAuthmethod() {
        return authmethod;
    }

    public void setAuthmethod(String authmethod) {
        this.authmethod = authmethod;
    }
}