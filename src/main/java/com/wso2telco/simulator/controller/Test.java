package com.wso2telco.simulator.controller;

/**
 * Created by isuru dilshan on 9/22/16.
 */
public class Test {

    private String name;
    private Test2 test2;

    public Test(String name, Test2 test2) {
        this.name = name;
        this.test2 = test2;
    }

    public Test2 getTest2() {
        return test2;
    }

    public void setTest2(Test2 test2) {
        this.test2 = test2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
