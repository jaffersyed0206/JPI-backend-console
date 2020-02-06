package com.apigroups.api.requests.authentication.users.editusers;

public class Editusers {
    private String email;
    private String firstname;
    private String lastname;
    private String username;
    private String usertoken;
    private Object mapJson;
    private Object visuals;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Object getMapJson() {
        return mapJson;
    }

    public void setMapJson(Object mapJson) {
        this.mapJson = mapJson;
    }

    public Object getVisuals() {
        return visuals;
    }

    public void setVisuals(Object visuals) {
        this.visuals = visuals;
    }
}