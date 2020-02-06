package com.apigroups.api.requests.authentication.users.registerforms;

import java.util.List;

public class Register {
    private String email;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String usertoken;
    private Object mapJson;
    private List<String> mapNames;
    private Object visuals;
    private String authmethod;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getMapJson() {
        return mapJson;
    }

    public void setMapJson(Object mapJson) {
        this.mapJson = mapJson;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public List<String> getMapNames() {
        return mapNames;
    }

    public void setMapNames(List<String> mapNames) {
        this.mapNames = mapNames;
    }

    public Object getVisuals() {
        return visuals;
    }

    public void setVisuals(Object visuals) {
        this.visuals = visuals;
    }

    public String getAuthmethod() {
        return authmethod;
    }

    public void setAuthmethod(String authmethod) {
        this.authmethod = authmethod;
    }
}