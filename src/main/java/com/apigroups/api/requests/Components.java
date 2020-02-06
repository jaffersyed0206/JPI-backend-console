package com.apigroups.api.requests;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Components{
    @Id
    private String id;
    private String componentname;
    private String componenttype;
    private List<String> componenttags;
    private String description;
    private String model;
    private List<Map<String, Object>> install;
    private String subcomponent;

    public String getComponentname() {
        return componentname;
    }

    public void setComponentname(String componentname) {
        this.componentname = componentname;
    }

    public List<String> getComponenttags() {
        return componenttags;
    }

    public void setComponenttags(List<String> componenttags) {
        this.componenttags = componenttags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<Map<String, Object>> getInstall() {
        return install;
    }

    public void setInstall(List<Map<String, Object>> install) {
        this.install = install;
    }



    public String getComponenttype() {
        return componenttype;
    }

    public void setComponenttype(String componenttype) {
        this.componenttype = componenttype;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcomponent() {
        return subcomponent;
    }

    public void setSubcomponent(String subcomponent) {
        this.subcomponent = subcomponent;
    }

   



}