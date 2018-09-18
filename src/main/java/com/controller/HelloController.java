package com.controller;

import com.service.HelloServiceImpl;
import com.service.HelloServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloServiceImpl helloService;

    @Autowired
    private HelloServiceImpl2 helloService2;

    @RequestMapping("/master")
    public String hello(){
        return helloService.hello();
    }

    @RequestMapping("/slave")
    public String hello2(){
        return helloService2.hello();
    }
}
