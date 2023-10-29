package com.example.CMS_01.Web;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class HomePageController {
    UserService userService;

//    A basic homepage so we can start from somewhere----------
    @GetMapping("/")
    public String getIndex(){return "/home/index.html";}

//    main courses
    @GetMapping("/SWE")
    public String getSWE(){return "Classes/SWE/Subject1.html";}
    @GetMapping("/Cpp")
    public String getCpp(){return "Classes/C++/Subject2.html";}
    @GetMapping("/Algorithms")
    public String getAlgorithms(){return "Classes/Algorithms/Subject3.html";}


//    login

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id).getUsername(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@Validated @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping("/Cpp/Quiz1")
    public String getCppQuiz1(){return "/Classes/C++/Content/Quizes/Quiz1.html";}

    @GetMapping("/Algorithms/Quiz1")
    public String getAlgorithmsQuiz1(){return "/Classes/Algorithms/Content/Quizes/Quiz1.html";}

//    articles
    @GetMapping("/SWE/article1")
    public String getSweArticle(){return "/Classes/SWE/Content/Article1.html";}

    @GetMapping("/Cpp/article1")
    public String getCppArticle(){return "/Classes/C++/Content/Article1.html";}

    @GetMapping("/Algorithms/article1")
    public String getAlgorithmsArticle(){return "/Classes/Algorithms/Content/Article1.html";}
}
