package com.example.CMS_01.Service;

import com.example.CMS_01.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends Service {
    User getUser(Long id);
    User saveUser(User user);
    void deleteStudent(Long id);
    List<User> getUsers();
}
