package com.apigroups.api.requests.projectanalytics.tables;

public class Tableresponse {
    private String reponseid;
    private Object response;
    private String error;

    public String getReponseid() {
        return reponseid;
    }

    public void setReponseid(String reponseid) {
        this.reponseid = reponseid;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}