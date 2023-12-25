package com.example.CMS_01.Service;


import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;

public interface UserService {
    //wipe every thing (DANGEROUS)
    void destroy();

    User getUser(Long id);


    User getUser(String username);
    User saveUser(User user);
    void setUser(String user);
    User getCurrentUser();
    Boolean SWEArticle();
    Boolean SWEVideo();
    Boolean SWEQuiz();

    Boolean CppArticle();
    Boolean CppVideo();
    Boolean CppQuiz();

    Boolean AlgorithmsArticle();
    Boolean AlgorithmsVideo();
    Boolean AlgorithmsQuiz();

    void deleteUserByUsername(String username);

    boolean checkUserAndSecurityInfo(UserWrapper userWrapper);
}