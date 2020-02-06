package com.apigroups.api.requests.projectanalytics.tables;

import java.util.List;

public class Tablerequest {
    private Object request;
    private List<String> labels;
    private String apiKey;

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

   
}