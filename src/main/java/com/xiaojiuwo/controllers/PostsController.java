package com.xiaojiuwo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuhaibao on 15/10/31.
 */
@Controller
@RequestMapping("/posts")
public class PostsController {


    @RequestMapping(value={"","/"}, method=RequestMethod.GET)
    public String index(){

        return "posts/index";

    }

}
