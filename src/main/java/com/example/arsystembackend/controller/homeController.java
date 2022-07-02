package com.example.arsystembackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/")
    public String HomeControllerAPI() {
        return "Hello world";
    }
}
