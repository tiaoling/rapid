package com.xiaojiuwo.controllers;

import com.xiaojiuwo.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuhaibao on 15/10/31.
 */
@Controller
@RequestMapping("/")
public class HomeController {



    @Autowired
    StationsService stationsService;


    @RequestMapping(value={"","/"})
    public String index(){

        return "home/index";

    }

}
