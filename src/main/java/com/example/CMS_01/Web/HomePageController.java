package com.example.CMS_01.Web;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.Service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class HomePageController {
    UserServiceImpl service;

//    A basic homepage so we can start from somewhere----------
    @GetMapping("/")
    public String getHomePage(){
        return "/index.html";
    }

//    main courses
    @GetMapping("/SWE")
    public String getSWE(){return "Classes/SWE/Subject1.html";}
    @GetMapping("/cpp")
    public String getCpp(){return "Classes/C++/Subject2.html";}
    @GetMapping("/Algorithms")
    public String getAlgorithms(){return "Classes/Algorithms/Subject3.html";}
    

//    login
    @RequestMapping("/register")
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
}

//  Videos
  @GetMapping("/SWE/Video1")
    public String getSWEVideo1(){return "/Classes/SWE/Content/Videos/Video1.html";}

    @GetMapping("/Cpp/Video1")
    public String getCppVideo1(){return "/Classes/C++/Content/Videos/Video1.html";}

    @GetMapping("/Algorithms/Video1")
    public String getAlgorithmsVideo1(){return "/Classes/Algorithms/Content/Videos/Video1.html";}

//  Quizess
    @GetMapping("/SWE/Quiz1")
    public String getSWEQuiz1(){return "/Classes/SWE/Content/Quizes/Quiz1.html";}

    @GetMapping("/Cpp/Articles")
    public String getCppQuiz1(){return "/Classes/C++/Content/Quizes/Quiz1.html";}

    @GetMapping("/Algorithms/Articles")
    public String getAlgorithmsQuiz1(){return "/Classes/Algorithms/Content/Quizes/Quiz1.html";}

}
