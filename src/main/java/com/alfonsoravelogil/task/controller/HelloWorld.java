package com.alfonsoravelogil.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/hello-world")
    public String helloWorldEndpoint(){
        return "Primer ejemplo";
    }
}
