package com.benjamin.smarterp.controller;

import com.benjamin.smarterp.service.CommonUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    public void index(){

    }

    @RequestMapping("version")
    public String version(){
        return CommonUnit.getVersion();
    }
}
