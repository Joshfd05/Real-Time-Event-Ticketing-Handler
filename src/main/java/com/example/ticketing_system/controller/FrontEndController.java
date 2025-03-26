package com.example.ticketing_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FrontEndController {

    // This controller handles all requests to paths without extensions
    @GetMapping("/{path:[^\\.]*}")
    public String redirectToIndex(@PathVariable String path) {
        return "forward:/index.html";
    }   // "forward" ensures the Angular app handles routing
}
