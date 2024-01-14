package com.example.CMS_01.Service;


import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;

import java.util.List;

public interface UserService {
    //wipe every thing (DANGEROUS)
    void destroy();

    User getUser(Long id);



    List<User> getAllUsers();
    User getUser(String username);
    User saveUser(User user);
    void setUser(String user);
    User getCurrentUser();

    Boolean SWEQuiz();

    Boolean CppQuiz();

    Boolean AlgorithmsQuiz();

    void deleteUserByUsername(String username);

    boolean checkUserAndSecurityInfo(UserWrapper userWrapper);
}