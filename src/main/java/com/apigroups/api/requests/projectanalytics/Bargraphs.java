package com.apigroups.api.requests.projectanalytics;

import java.util.List;

public class Bargraphs {
    private String bargraphname;
    private String bargraphid;
    private List<String> bargraphdevs;
    private List<String> barviewers;
    private String bardescription;
    private List<Object> bardata;
    private String barapi;

    public String getBargraphname() {
        return bargraphname;
    }

    public void setBargraphname(String bargraphname) {
        this.bargraphname = bargraphname;
    }

    public String getBargraphid() {
        return bargraphid;
    }

    public void setBargraphid(String bargraphid) {
        this.bargraphid = bargraphid;
    }

    public List<String> getBargraphdevs() {
        return bargraphdevs;
    }

    public void setBargraphdevs(List<String> bargraphdevs) {
        this.bargraphdevs = bargraphdevs;
    }

    public List<String> getBarviewers() {
        return barviewers;
    }

    public void setBarviewers(List<String> barviewers) {
        this.barviewers = barviewers;
    }

    public String getBardescription() {
        return bardescription;
    }

    public void setBardescription(String bardescription) {
        this.bardescription = bardescription;
    }

    public List<Object> getBardata() {
        return bardata;
    }

    public void setBardata(List<Object> bardata) {
        this.bardata = bardata;
    }

    public String getBarapi() {
        return barapi;
    }

    public void setBarapi(String barapi) {
        this.barapi = barapi;
    }

  

    
}