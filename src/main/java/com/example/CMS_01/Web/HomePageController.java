package com.example.CMS_01.Web;

import com.example.CMS_01.Entity.DiscussionPosts;
import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;
import com.example.CMS_01.Repository.DiscussionRepository;
//import com.example.CMS_01.Service.DiscussionPostService;
import com.example.CMS_01.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@ComponentScan(basePackages = {"com.example.CMS_01", "com.example.CMS_01.Service.UserServiceImpl"})
public class HomePageController {

    @NonNull
    private UserService userService;
    @NonNull
    private DiscussionRepository discussionPostRepository;
    @NonNull
//    private DiscussionPostService discussionPostService;


//    api requests
    @RequestMapping("/api/Users")
    public void giveUserTrophy(@RequestParam String course){
        switch (course){
            case "SWE" :
                userService.SWEQuiz();
                System.out.println("SWE trophy given to "+userService.getCurrentUser().getUsername());
                break;
            case "Algorithms" :
                userService.AlgorithmsQuiz();
                System.out.println("Algorithms trophy given to "+userService.getCurrentUser().getUsername());
                break;
            case  "Cpp" :
                userService.CppQuiz();
                System.out.println("Cpp trophy given to "+userService.getCurrentUser().getUsername());
                break;
        }
    }

    @RequestMapping("/api/Users/Trophies")
    @ResponseBody
    public String getUserTrophy(@RequestParam String course){
        switch (course){
            case "SWE" :
                System.out.println("render SWE trophy "+userService.getCurrentUser().getUsername());
                if(userService.getCurrentUser().getSWEQuiz().equals("1"))
                    return "1";
              break;
            case "Algorithms" :
                System.out.println("render Algorithms trophy "+userService.getCurrentUser().getUsername());
                if(userService.getCurrentUser().getAlgorithmsQuiz().equals("1"))
                    return "1";
                break;
            case  "Cpp" :
                System.out.println("render Cpp trophy "+userService.getCurrentUser().getUsername());
                if(userService.getCurrentUser().getCppQuiz().equals("1"))
                    return "1";
                break;
       }
        return "";
    }






    @RequestMapping("/api/Users/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


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

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(@RequestParam String username){
        return userService.getUser(username);
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
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            System.out.println("inside save user " + user.getUsername());
            return ResponseEntity.ok("{\"message\": \"User saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error saving user\"}");
        }
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
