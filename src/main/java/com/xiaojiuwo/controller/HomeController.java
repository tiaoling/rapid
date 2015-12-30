package com.xiaojiuwo.controller;

import com.alibaba.fastjson.JSON;
import com.xiaojiuwo.models.AreaResult;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
