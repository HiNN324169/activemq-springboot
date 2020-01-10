package com.nn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "hello")
    public @ResponseBody Object hello(){
        return "hello spring boot ！！！";
    }
}
