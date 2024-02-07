package com.ktds.sport.debate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }


    @GetMapping("/write")
    public String getContactPage() {
        return "write";
    }

    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/post")
    public String getPostPage() {
        return "post";
    }
}
