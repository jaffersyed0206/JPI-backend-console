package com.apigroups.api.apicontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/test")
@RestController 
public class Test {
  @GetMapping("/testtheapi")
  public Object GetThis() {
    Map<String , Object> response = new HashMap<>();
    response.put("name", "Jaffer");
    response.put("last", "Syed");
    response.put("birthday", "Feb 6th");
    return response;
  }
}
