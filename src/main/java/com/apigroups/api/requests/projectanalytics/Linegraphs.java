package com.apigroups.api.requests.projectanalytics;

import java.util.List;

public class Linegraphs {
    private String linegraphname;
    private String lineid;
    private List<String> linedevs;
    private List<String> lineviewers;
    private String linedescriptions;
    private List<Object> linedata;
    private String lineapi;

    public String getLinegraphname() {
        return linegraphname;
    }

    public void setLinegraphname(String linegraphname) {
        this.linegraphname = linegraphname;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public List<String> getLinedevs() {
        return linedevs;
    }

    public void setLinedevs(List<String> linedevs) {
        this.linedevs = linedevs;
    }

    public List<String> getLineviewers() {
        return lineviewers;
    }

    public void setLineviewers(List<String> lineviewers) {
        this.lineviewers = lineviewers;
    }

    public String getLinedescriptions() {
        return linedescriptions;
    }

    public void setLinedescriptions(String linedescriptions) {
        this.linedescriptions = linedescriptions;
    }

    public List<Object> getLinedata() {
        return linedata;
    }

    public void setLinedata(List<Object> linedata) {
        this.linedata = linedata;
    }

    public String getLineapi() {
        return lineapi;
    }

    public void setLineapi(String lineapi) {
        this.lineapi = lineapi;
    }
    

}