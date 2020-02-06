package com.apigroups.api.requests.projectanalytics;

import java.util.List;

import com.apigroups.api.requests.projectanalytics.tables.Tableresponse;
import com.apigroups.api.requests.projectanalytics.tables.Tableusuage;

public class Tables {
    private String tablename;
    private List<String> tablelables;
    private String tableid;
    private List<String> tabledevs;
    private List<String> tableviewers;
    private String tabledescription;
    private List<Tableresponse> tabledata;
    private String tableapi;
    private Tableusuage tableusuage;
    

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    

    public String getTabledescription() {
        return tabledescription;
    }

    public void setTabledescription(String tabledescription) {
        this.tabledescription = tabledescription;
    }


    public List<String> getTabledevs() {
        return tabledevs;
    }

    public void setTabledevs(List<String> tabledevs) {
        this.tabledevs = tabledevs;
    }

    public List<String> getTableviewers() {
        return tableviewers;
    }

    public void setTableviewers(List<String> tableviewers) {
        this.tableviewers = tableviewers;
    }

    public List<String> getTablelables() {
        return tablelables;
    }

    public void setTablelables(List<String> tablelables) {
        this.tablelables = tablelables;
    }

    public String getTableapi() {
        return tableapi;
    }

    public void setTableapi(String tableapi) {
        this.tableapi = tableapi;
    }

    public List<Tableresponse> getTabledata() {
        return tabledata;
    }

    public void setTabledata(List<Tableresponse> tabledata) {
        this.tabledata = tabledata;
    }

    public Tableusuage getTableusuage() {
        return tableusuage;
    }

    public void setTableusuage(Tableusuage tableusuage) {
        this.tableusuage = tableusuage;
    }

  

    

  

}