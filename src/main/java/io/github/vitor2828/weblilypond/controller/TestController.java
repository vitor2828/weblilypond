package io.github.vitor2828.weblilypond.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TestController {
    
    @GetMapping("/")
    public String home() {
        return "Spring Boot lives...";
    }

    @PostMapping("/compile")
    public String compile(@RequestBody String body) {
        return "ok";
    }
}
