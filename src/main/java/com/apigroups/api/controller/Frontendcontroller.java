package com.apigroups.api.controller;

import java.util.List;

import com.apigroups.api.repo.Frontendrepo;
import com.apigroups.api.requests.Frontendcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/frontend")
@RestController
public class Frontendcontroller {
   @Autowired
   private Frontendrepo frontrepo;
   @GetMapping("/getcomponents")
   public List<Frontendcomponents> GetFrontEndComps() {
      List<Frontendcomponents> frontendcomponents = frontrepo.findAll();
       return frontendcomponents;
   }
}