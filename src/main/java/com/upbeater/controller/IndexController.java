package com.upbeater.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/")
public class IndexController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String indexController(){
        return "Web Service Started.!";
    }
}
