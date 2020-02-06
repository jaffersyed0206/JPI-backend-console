package com.apigroups.api.functions.tables;

import java.util.List;

import com.apigroups.api.requests.projectanalytics.Tables;
import com.apigroups.api.requests.projectanalytics.tables.Tableresponse;

public class Tablefunctions {
    public static Tables findTable(List<Tables> tablearray , String tableapi) {
        for (Tables doc: tablearray) {
            if (doc.getTableapi().equals(tableapi) == true) {
                return doc;
            }
        }
        return null;
    } 
    public static Tableresponse findTableData(List<Tableresponse>  tabledata, String tabledataid) {
        for (Tableresponse docs: tabledata) {
            if (docs.getReponseid().equals(tabledataid) == true) {
                return docs;
            }
        }
        return null;
    }
}