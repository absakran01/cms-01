package com.example.CMS_01.Service;


import com.example.CMS_01.Entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);

}