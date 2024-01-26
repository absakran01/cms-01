package com.example.CMS_01.Web;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;
import com.example.CMS_01.Service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@ComponentScan(basePackages = {"com.example.CMS_01", "com.example.CMS_01.Service.UserServiceImpl"})
public class HomePageController {

    @NonNull
    private UserService userService;




    //    A basic homepage so we can start from somewhere----------
    @RequestMapping("/")
    public String getIndex(){

        String username;
//         Access authentication information during initialization
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        userService.setUser(username);
        System.out.println("-----------Initialized with username: " + userService.getCurrentUser().getUsername());
        for (int i = 0; i < 20; i++) {
            System.out.println(username);
        }

        return "/home/index.html";}


//    Discussion posts methods
//    @PostMapping("/savePost")
//    public String savePost(@ModelAttribute("post") String post) {
//        // Save the post to the database
//        post.add(post);
//
//        return "redirect:Algorithms/Discussion";
//    }



//    main courses
    @GetMapping("/SWE")
    public String getSWE(){return "Classes/SWE/Subject1.html";}
    @GetMapping("/Cpp")
    public String getCpp(){return "Classes/C++/Subject2.html";}
    @GetMapping("/Algorithms")
    public String getAlgorithms(){return "Classes/Algorithms/Subject3.html";}




    //wipe out every thing [DANGEROUS]
    @ResponseBody
    @GetMapping("/5576")
    public String destroy(){
        userService.destroy();
        return "-----------------------";
    }



//  Videos
  @GetMapping("/SWE/Video1")
    public String getSWEVideo1(){
        return "/Classes/SWE/Content/Videos/Video1.html";}

    @GetMapping("/Cpp/Video1")
    public String getCppVideo1(){
        return "/Classes/C++/Content/Videos/Video1.html";}

    @GetMapping("/Algorithms/Video1")
    public String getAlgorithmsVideo1(){
        return "/Classes/Algorithms/Content/Videos/Video1.html";}

//  Quizess
    @GetMapping("/SWE/Quiz1")
    public String getSWEQuiz1(){
        userService.SWEQuiz();
        return "/Classes/SWE/Content/Quizes/Quiz1.html";}

    @GetMapping("/Cpp/Quiz1")
    public String getCppQuiz1(){
        userService.CppQuiz();
        return "/Classes/C++/Content/Quizes/Quiz1.html";}

    @GetMapping("/Algorithms/Quiz1")
    public String getAlgorithmsQuiz1(){
//        userService.AlgorithmsQuiz();
        return "/Classes/Algorithms/Content/Quizes/Quiz1.html";}

//    articles
    @GetMapping("/SWE/article1")
    public String getSweArticle(){
        return "/Classes/SWE/Content/Article1.html";}

    @GetMapping("/Cpp/article1")
    public String getCppArticle(){
        return "/Classes/C++/Content/Article1.html";}

    @GetMapping("/Algorithms/article1")
    public String getAlgorithmsArticle(){
        return "/Classes/Algorithms/Content/Article1.html";}

//    discussion boards
    @RequestMapping("/SWE/Discussion")
    public String GetSWEDiscussion(){

        return "/Classes/SWE/Content/THE-Discussionboard/SWE-DB.html";
    }

    @RequestMapping("/Cpp/Discussion")
    public String GetCppDiscussion(){

        return "/Classes/C++/Content/THE-Discussionboard/C++-DB.html";
    }

    @GetMapping("/Algorithms/Discussion")
    public String GetAlgorithmsDiscussion(){

        return "/Classes/Algorithms/Content/THE-Discussionboard/Algorithms-DB.html";
    }

}
