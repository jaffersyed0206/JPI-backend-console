package com.apigroups.api.requests.projectanalytics.datapages;

import java.util.List;

import com.apigroups.api.requests.projectanalytics.tables.Tableresponse;

public class Tablepage {
    private String tablename;
    private String tableapi;
    private String tabledescription;
    private List<String> dataviewers;
    private List<String> datadevs;
    private List<Tableresponse> tabledata;
    private String projectname;
    private List<String> tablelabels;

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getTableapi() {
        return tableapi;
    }

    public void setTableapi(String tableapi) {
        this.tableapi = tableapi;
    }

    public String getTabledescription() {
        return tabledescription;
    }

    public void setTabledescription(String tabledescription) {
        this.tabledescription = tabledescription;
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

    public List<Tableresponse> getTabledata() {
        return tabledata;
    }

    public void setTabledata(List<Tableresponse> tabledata) {
        this.tabledata = tabledata;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public List<String> getTablelabels() {
        return tablelabels;
    }

    public void setTablelabels(List<String> tablelabels) {
        this.tablelabels = tablelabels;
    }
}
