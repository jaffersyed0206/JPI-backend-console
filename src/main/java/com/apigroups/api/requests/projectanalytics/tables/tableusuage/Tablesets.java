package com.apigroups.api.requests.projectanalytics.tables.tableusuage;

import java.util.Date;
import java.util.List;

import com.apigroups.api.requests.projectanalytics.tables.tableusuage.tablesets.Tablesetitem;

public class Tablesets {
    private String tablemethod;
    private String totalMethodId;
    private Date methoddate;
    private List<Tablesetitem> methoddata; 

    public String getTablemethod() {
        return tablemethod;
    }

    public void setTablemethod(String tablemethod) {
        this.tablemethod = tablemethod;
    }

    public String getTotalMethodId() {
        return totalMethodId;
    }

    public void setTotalMethodId(String totalMethodId) {
        this.totalMethodId = totalMethodId;
    }

    public Date getMethoddate() {
        return methoddate;
    }

    public void setMethoddate(Date methoddate) {
        this.methoddate = methoddate;
    }

    public List<Tablesetitem> getMethoddata() {
        return methoddata;
    }

    public void setMethoddata(List<Tablesetitem> methoddata) {
        this.methoddata = methoddata;
    }


}