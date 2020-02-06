package com.apigroups.api.controller;

import com.apigroups.api.repo.Backendrepo;
import com.apigroups.api.repo.Componentrepo;
import com.apigroups.api.repo.Frontcompliancerepo;
import com.apigroups.api.repo.Frontendrepo;
import com.apigroups.api.requests.Backendcomponents;
import com.apigroups.api.requests.Components;
import com.apigroups.api.requests.Frontendcompliance;
import com.apigroups.api.requests.Frontendcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/adminapi")
@RestController
public class Componentcontroller {
    @Autowired
    private Componentrepo componentrepo;
    @Autowired
    private Frontendrepo frontendrepo;
    @Autowired
    private Backendrepo backendrepo;
    @Autowired
    private Frontcompliancerepo frontcompliancerepo;
    @PutMapping("/setcomponent")
    public Object CreateProject(@RequestBody Components request) {
        Components newproject = new Components();
        newproject.setComponentname(request.getComponentname());
        newproject.setComponenttags(request.getComponenttags());
        newproject.setComponenttype(request.getComponenttype());
        newproject.setDescription(request.getDescription());
        newproject.setInstall(request.getInstall());
        newproject.setModel(request.getModel());
        if(request.getComponenttype().equals("FRONTEND") == true) {
            Frontendcomponents frontendproject = new Frontendcomponents();
            frontendproject.setComponentname(request.getComponentname());
            frontendproject.setComponenttags(request.getComponenttags());
            frontendproject.setComponenttype(request.getComponenttype());
            frontendproject.setDescription(request.getDescription());
            frontendproject.setInstall(request.getInstall());
            frontendproject.setModel(request.getModel());
            frontendproject.setLanguange(request.getSubcomponent());
            frontendrepo.save(frontendproject);
        } else if (request.getComponenttype().equals("BACKEND") == true) {
            Backendcomponents backendcomponents = new Backendcomponents();
            backendcomponents.setComponentname(request.getComponentname());
            backendcomponents.setComponenttags(request.getComponenttags());
            backendcomponents.setComponenttype(request.getComponenttype());
            backendcomponents.setDescription(request.getDescription());
            backendcomponents.setInstall(request.getInstall());
            backendcomponents.setModel(request.getModel());
            backendcomponents.setLanguange(request.getSubcomponent());
            backendrepo.save(backendcomponents);
        } else if (request.getComponenttype().equals("FRONTCOMPLIANCE") == true) {
            Frontendcompliance frontendcompliance = new Frontendcompliance();
            frontendcompliance.setComponentname(request.getComponentname());
            frontendcompliance.setComponenttags(request.getComponenttags());
            frontendcompliance.setComponenttype(request.getComponenttype());
            frontendcompliance.setDescription(request.getDescription());
            frontendcompliance.setInstall(request.getInstall());
            frontendcompliance.setModel(request.getModel());
            frontendcompliance.setLanguange(request.getSubcomponent());
            frontcompliancerepo.save(frontendcompliance);
        }
        componentrepo.save(newproject);
        return null;
    }
}