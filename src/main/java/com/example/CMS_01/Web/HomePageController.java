package com.example.CMS_01.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

//    A basic homepage so we can start from somewhere----------
    @GetMapping("/")
    public String getHomePage(){
        return "/index.html";
    }

    @GetMapping("/SWE")
    public String getSWE(){return "Classes/SWE/Subject.html";}

    @GetMapping("/C++")
    public String getCpp(){return "Classes/C++/Subject.html";}

    @GetMapping("/Algorithms")
    public String getAlgorithms(){return "Classes/Algorithms/Subject.html";}


    @GetMapping("/SWE/Articles")
    public String getSWEArticle(){return "/Classes/SWE/Content/Articles.html";}

    @GetMapping("/C++/Articles")
    public String getCppArticle(){return "/Classes/C++/Content/Articles.html";}

    @GetMapping("/Algorithms/Articles")
    public String getAlgorithmsArticle(){return "/Classes/Algorithms/Content/Articles.html";}


}
