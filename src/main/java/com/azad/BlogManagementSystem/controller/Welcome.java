package com.azad.BlogManagementSystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
@CrossOrigin(origins = "http://localhost:5173")
public class Welcome {

    @GetMapping
    public String welcome(){
        return "Welcome in my app";
    }
}
