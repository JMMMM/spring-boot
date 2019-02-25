package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello2")
public class Hello2Controller {
    @RequestMapping("/fuck")
    public void fuck(){
    }
}
