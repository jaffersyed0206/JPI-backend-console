package com.apigroups.api.requests.projectanalytics.tables.tableusuage;

import java.util.Date;
import java.util.List;

import com.apigroups.api.requests.projectanalytics.tables.tableusuage.tablegetdata.Tablegetitem;

public class Tablegetdata {
    private String tablemethod;
    private String totalMethodId;
    private Date methoddate;
    private List<Tablegetitem> methoddata;

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

    public List<Tablegetitem> getMethoddata() {
        return methoddata;
    }

    public void setMethoddata(List<Tablegetitem> methoddata) {
        this.methoddata = methoddata;
    }
}