package com.wso2telco.simulator.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@ComponentScan
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    @ResponseBody
    public Test greeting(@RequestBody Test test) {
        return test;
    }
}
