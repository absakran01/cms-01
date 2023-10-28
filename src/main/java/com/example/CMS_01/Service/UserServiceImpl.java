package com.example.CMS_01.Service;

import com.example.CMS_01.Entity.Role;
import com.example.CMS_01.Entity.User;
import com.example.CMS_01.Repository.RoleRepository;
import com.example.CMS_01.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private RoleServiceImpl roleService;
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if(user==null){
            new UsernameNotFoundException("User not exists by Username");
        }
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).isPresent() ? null : userRepository.findById(id).get();
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveUserAndRole(User user, Role role) {
        user.setRoles((Set<Role>) role);
        roleService.saveRole(role);
        return userRepository.save(user);
    }

    @Override
    public void deleteStudent(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
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
