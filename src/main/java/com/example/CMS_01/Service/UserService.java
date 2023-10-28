package com.example.CMS_01.Service;

import com.example.CMS_01.Entity.Role;
import com.example.CMS_01.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends Service {
    UserDetails loadUserByUsername(String username);
    User findUserById(Integer id);
    User getUser(Integer id);
    User saveUser(User user);
    User saveUserAndRole(User user, Role role);
    void deleteStudent(Integer id);
    List<User> getUsers();
}
