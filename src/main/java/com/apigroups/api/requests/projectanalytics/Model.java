package com.apigroups.api.requests.projectanalytics;

import java.util.List;

public class Model {
    private String modelid;
    private String modelname;
    private String modelapi;
    private String modeldescription;
    private List<String> dataviewers;
    private List<String> datadevs;
    private String datatype;

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getModeldescription() {
        return modeldescription;
    }

    public void setModeldescription(String modeldescription) {
        this.modeldescription = modeldescription;
    }

    public List<String> getDataviewers() {
        return dataviewers;
    }

    public void setDataviewers(List<String> dataviewers) {
        this.dataviewers = dataviewers;
    }

    public List<String> getDatadevs() {
        return datadevs;
    }

    public void setDatadevs(List<String> datadevs) {
        this.datadevs = datadevs;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getModelapi() {
        return modelapi;
    }

    public void setModelapi(String modelapi) {
        this.modelapi = modelapi;
    }
}