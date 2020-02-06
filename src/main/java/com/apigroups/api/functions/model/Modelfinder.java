package com.apigroups.api.functions.model;

import java.util.List;

import com.apigroups.api.requests.projectanalytics.Model;

public class Modelfinder {
    public static Model GetModel(List<Model> modelarray, String modelapi) {
        for (Model docs: modelarray) {
            if (docs.getModelapi().equals(modelapi) == true) {
                return docs;
            }
        }
        return null;
    }
}