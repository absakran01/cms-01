package com.example.CMS_01.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

//    A basic homepage so we can start from somewhere.
    @GetMapping("/")
    public String getHomePage(){
        return "index.html";
    }

}
