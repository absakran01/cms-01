package com.example.CMS_01.Service;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Override
    public User getUser(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
