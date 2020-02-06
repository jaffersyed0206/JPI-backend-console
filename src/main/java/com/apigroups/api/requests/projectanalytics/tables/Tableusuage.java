package com.apigroups.api.requests.projectanalytics.tables;

import java.util.List;

import com.apigroups.api.requests.projectanalytics.tables.tableusuage.Tablefetchdetails;
import com.apigroups.api.requests.projectanalytics.tables.tableusuage.Tablegetdata;
import com.apigroups.api.requests.projectanalytics.tables.tableusuage.Tablesets;

public class Tableusuage {
   private List<Tablesets> tablesets;
   private List<Tablegetdata> tablegets;
   private List<Tablefetchdetails> tablefetchs;
 
    public List<Tablesets> getTablesets() {
        return tablesets;
    }

    public void setTablesets(List<Tablesets> tablesets) {
        this.tablesets = tablesets;
    }

    public List<Tablegetdata> getTablegets() {
        return tablegets;
    }

    public void setTablegets(List<Tablegetdata> tablegets) {
        this.tablegets = tablegets;
    }

    public List<Tablefetchdetails> getTablefetchs() {
        return tablefetchs;
    }

    public void setTablefetchs(List<Tablefetchdetails> tablefetchs) {
        this.tablefetchs = tablefetchs;
    }
}