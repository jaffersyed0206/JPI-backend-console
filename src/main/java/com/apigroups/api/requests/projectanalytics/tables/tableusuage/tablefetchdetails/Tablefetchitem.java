package com.apigroups.api.requests.projectanalytics.tables.tableusuage.tablefetchdetails;

import java.util.Date;

public class Tablefetchitem {
    private String methodid;
    private Date methodran;

    public String getMethodid() {
        return methodid;
    }

    public void setMethodid(String methodid) {
        this.methodid = methodid;
    }

    public Date getMethodran() {
        return methodran;
    }

    public void setMethodran(Date methodran) {
        this.methodran = methodran;
    }
}