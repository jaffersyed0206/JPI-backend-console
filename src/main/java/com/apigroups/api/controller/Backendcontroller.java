package com.apigroups.api.controller;

import java.util.List;

import com.apigroups.api.repo.Backendrepo;
import com.apigroups.api.requests.Backendcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/backend")
@RestController
public class Backendcontroller {
    @Autowired
    private Backendrepo backendrepo;

    @GetMapping("/getbackendcomps")
    public List<Backendcomponents> GetBackeEndComps() {
        List<Backendcomponents> becomps = backendrepo.findAll();
        return becomps;
    }
}