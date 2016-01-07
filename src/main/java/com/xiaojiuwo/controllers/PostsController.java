package com.xiaojiuwo.controllers;


import com.xiaojiuwo.models.Post;
import com.xiaojiuwo.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liuhaibao on 15/10/31.
 */
@Controller
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @RequestMapping(value={"","/"}, method = RequestMethod.GET)
    public String index(Model model,HttpServletResponse response, @CookieValue(value = "foo", defaultValue = "hello") String fooCookie){
        List<Post> posts = postsService.findAll();
        model.addAttribute("posts",posts);
        response.addCookie(new

                Cookie("foo",
                "bar"));
        return "posts/index";
    }
    @RequestMapping(value={"/new"},  method= RequestMethod.GET)
    public String newPost(Model model){
        model.addAttribute("post", new Post());
        return "posts/new";
    }

    @RequestMapping(value={"","/"},  method= RequestMethod.POST)
    public String create(Post post){
        postsService.save(post);
        return "redirect:posts";
    }
}
