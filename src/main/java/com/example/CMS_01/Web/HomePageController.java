package com.example.CMS_01.Web;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;
import com.example.CMS_01.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Controller
@ComponentScan(basePackages = {"com.example.CMS_01", "com.example.CMS_01.Service.UserServiceImpl"})
public class HomePageController {

    @NonNull
    UserService userService;

//    A basic homepage so we can start from somewhere----------
    @RequestMapping("/")
    public String getIndex(){
        String username;
        // Access authentication information during initialization
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        userService.setUser(username);
        System.out.println("-----------Initialized with username: " + userService.getCurrentUser().getUsername());
        for (int i = 0; i < 20; i++) {
            System.out.println(username);
        }
        return "/home/index.html";}

//    main courses
    @GetMapping("/SWE")
    public String getSWE(){return "Classes/SWE/Subject1.html";}
    @GetMapping("/Cpp")
    public String getCpp(){return "Classes/C++/Subject2.html";}
    @GetMapping("/Algorithms")
    public String getAlgorithms(){return "Classes/Algorithms/Subject3.html";}


//    login&reg
//remove comment when html is ready
    @GetMapping("/login")
    public String getLogIn(){return "/Login&reg/LoginScreen.html";}

    @GetMapping("/login/error")
    public String getLogInFail(){return "/Login&reg/LoginFail.html";}

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id).getUsername(), HttpStatus.OK);
    }


    //wipe out every thing [DANGEROUS]
    @ResponseBody
    @GetMapping("/5576")
    public String destroy(){
        userService.destroy();
        return "-----------------------";
    }

    @PostMapping("/setUser")
    public String setUser(){
        String username;
        // Access authentication information during initialization
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        System.out.println("-----------Initialized with username: " + username+userService.getUser(username).getCppQuiz());
        userService.setUser(username);
        for (int i = 0; i < 20; i++) {
            System.out.println(username);
        }
        return "redirect:/";
    }

    @GetMapping("/Settings")
    public String getSettings(Model model){
        model.addAttribute("userWrapper",new UserWrapper());
        return "/home/AccountSettings/index.html";}

    @PostMapping("/updateUserAndSecurityInfo")
    public String updateUserAndSecurityInfo(@ModelAttribute("userWrapper") UserWrapper userWrapper) {
        // Access user and securityInfo using userWrapper
        try {
            userService.checkUserAndSecurityInfo(userWrapper);
          userService.saveUser(userWrapper.getNewCred());
          userService.deleteUserByUsername(userWrapper.getUser().getUsername());
        } catch (Exception e){return "/home/AccountSettings/indexFail.html";}
        return "redirect:/logout"; // Redirect to user profile or another appropriate page
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/Login&reg/RegisterScreen.html";
    }

    @GetMapping("/register/error")
    public String registerError(Model model) {
        model.addAttribute("user", new User());
        return "/Login&reg/RegisterScreenFail.html";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        try{
            userService.saveUser(user);
        System.out.println("inside save user " + user.getUsername());
        }
        catch (Exception e){return "redirect:/register/error";}
        return "redirect:/login";
    }

    @GetMapping("/deleteUser")
    public String DeleteUser(){
        try {
            userService.deleteUserByUsername(userService.getCurrentUser().getUsername());
        }catch (Exception e){;}
        return"redirect:/logout";
    }

//  Videos
  @GetMapping("/SWE/Video1")
    public String getSWEVideo1(){
        userService.SWEVideo();
        return "/Classes/SWE/Content/Videos/Video1.html";}

    @GetMapping("/Cpp/Video1")
    public String getCppVideo1(){
        userService.CppVideo();
        return "/Classes/C++/Content/Videos/Video1.html";}

    @GetMapping("/Algorithms/Video1")
    public String getAlgorithmsVideo1(){
        userService.AlgorithmsVideo();
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
        userService.AlgorithmsQuiz();
        return "/Classes/Algorithms/Content/Quizes/Quiz1.html";}

//    articles
    @GetMapping("/SWE/article1")
    public String getSweArticle(){
        userService.SWEArticle();
        return "/Classes/SWE/Content/Article1.html";}

    @GetMapping("/Cpp/article1")
    public String getCppArticle(){
        userService.CppArticle();
        return "/Classes/C++/Content/Article1.html";}

    @GetMapping("/Algorithms/article1")
    public String getAlgorithmsArticle(){
        userService.AlgorithmsArticle();
        return "/Classes/Algorithms/Content/Article1.html";}
}
