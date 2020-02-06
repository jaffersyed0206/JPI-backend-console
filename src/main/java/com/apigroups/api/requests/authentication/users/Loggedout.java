package com.apigroups.api.requests.authentication.users;

public class Loggedout {
    private String useruid;
    private Boolean status;

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}