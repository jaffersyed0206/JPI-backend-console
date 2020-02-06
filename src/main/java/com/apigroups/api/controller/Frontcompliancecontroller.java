package com.apigroups.api.controller;

import java.util.List;

import com.apigroups.api.repo.Frontcompliancerepo;
import com.apigroups.api.requests.Frontendcompliance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/frontcompliance")
@RestController
public class Frontcompliancecontroller {
    @Autowired
    private Frontcompliancerepo frontcompliancerepo;
    @GetMapping("/getfrontcompliance")
    public List<Frontendcompliance> GetFrontComps() {
        List<Frontendcompliance> response = frontcompliancerepo.findAll();
        return response;
    }
}