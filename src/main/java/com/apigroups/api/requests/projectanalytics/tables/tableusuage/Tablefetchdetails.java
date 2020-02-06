package com.apigroups.api.requests.projectanalytics.tables.tableusuage;

import java.util.Date;
import java.util.List;

import com.apigroups.api.requests.projectanalytics.tables.tableusuage.tablefetchdetails.Tablefetchitem;

public class Tablefetchdetails {
    private String tablemethod;
    private String totalMethodId;
    private Date methoddate;
    private List<Tablefetchitem> methoddata;

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

    public List<Tablefetchitem> getMethoddata() {
        return methoddata;
    }

    public void setMethoddata(List<Tablefetchitem> methoddata) {
        this.methoddata = methoddata;
    }
}