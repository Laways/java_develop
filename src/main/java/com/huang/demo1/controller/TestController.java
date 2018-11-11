package com.huang.demo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index (){
        sayHello();
        System.out.println("com.huang.demo1.controller.TestController");

        return "hello world";
    }
    private void sayHello() {
        System.out.println("hello world");
    }
}
