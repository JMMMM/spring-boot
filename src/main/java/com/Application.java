package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jimmy on 2017/5/7.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(Application.class,args);
    }
}