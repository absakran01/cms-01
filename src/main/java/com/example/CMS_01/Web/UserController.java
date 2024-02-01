package com.example.CMS_01.Web;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;
import com.example.CMS_01.Service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class UserController {



    @NonNull
    private UserService userService;




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

    @GetMapping("/Settings")
    public String getSettings(Model model){
        model.addAttribute("userWrapper",new UserWrapper());
        return "/home/AccountSettings/index.html";}

    @PostMapping("/api/users/update")
    public ResponseEntity<String> updateUser(@RequestBody UserWrapper userWrapper) {
        try {
            User existingUser = userWrapper.getUser();
            User newCredentials = userWrapper.getNewCred();
            userService.updateUser(existingUser, newCredentials);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user");
        }
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



    //    api requests
    @GetMapping("api/Users")
    public String giveUserTrophy(@RequestParam String course){
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
        return "succes";
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



    //send user progress to the front end
    @GetMapping("/api/progress")
    @ResponseBody
    public String getProgress() {
        System.out.println(Integer.valueOf(userService.getCurrentUser().getSWEQuiz()) * 34
                + Integer.valueOf(userService.getCurrentUser().getCppQuiz()) * 33
                + Integer.valueOf(userService.getCurrentUser().getAlgorithmsQuiz()) * 33);


        return (Integer.valueOf(userService.getCurrentUser().getSWEQuiz()) * 33) +
                (Integer.valueOf(userService.getCurrentUser().getCppQuiz()) * 33) +
                (Integer.valueOf(userService.getCurrentUser().getAlgorithmsQuiz()) * 33) + "";
    }





    @RequestMapping("/api/Users/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


}
