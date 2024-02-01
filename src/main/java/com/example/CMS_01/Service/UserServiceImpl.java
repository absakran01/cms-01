package com.example.CMS_01.Service;

import java.util.List;
import java.util.Optional;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.Repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    //wipe every thing (DANGEROUS)
    public void destroy(){
        userRepository.deleteAll();
    }




    private User user;
    @NonNull
    private UserRepository userRepository;
    @NonNull
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
       return (List<User>) userRepository.findAll();
    }


    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User userDTO = new User();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());



        userDTO.setAlgorithmsQuiz("");

        userDTO.setCppQuiz("");

        userDTO.setSWEQuiz("");

        return userRepository.save(userDTO);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else return  null;
    }


    public void setUser(String username) {
        user=this.getUser(username);
    }

    public User getCurrentUser() {
        return user;
    }

    /**
     * @return
     */
    @Override
    public Boolean SWEQuiz() {

        user.setSWEQuiz("1");
        userRepository.save(user);
        System.out.println(this.getUser(user.getUsername()).getSWEQuiz());
        return null;
    }

    /**
     * @return
     */
    @Override
    public Boolean CppQuiz() {

        user.setCppQuiz("1");
        userRepository.save(user);
        System.out.println(this.getUser(user.getUsername()).getCppQuiz());
        return null;
    }



    /**
     * @return
     */
    @Override
    public Boolean AlgorithmsQuiz() {

        user.setAlgorithmsQuiz("1");
        userRepository.save(user);
        System.out.println(this.getUser(user.getUsername()).getAlgorithmsQuiz());
        return null;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public void deleteUserByUsername(String username) {
       userRepository.deleteById(userRepository.findByUsername(username).get().getId());
       return;
    }

    @Override
    public User updateUser(User existingUser, User newCredentials)throws Exception{
        // Perform your logic to update the user here
        if (!(existingUser.getUsername().equals(getCurrentUser().getUsername()) ||
                bCryptPasswordEncoder.matches(existingUser.getPassword(), getCurrentUser().getPassword()))){
            throw new Exception("Wrong credentials");
        }
        User userDTO = new User();
        userDTO.setUsername(newCredentials.getUsername());
        userDTO.setPassword(bCryptPasswordEncoder.encode(newCredentials.getPassword()));
        userDTO.setAlgorithmsQuiz("");
        userDTO.setCppQuiz("");
        userDTO.setSWEQuiz("");

        userRepository.deleteById(getCurrentUser().getId());

        return userRepository.save(userDTO);
    }

    public void deleteUser(Long userId) {
        // Perform your logic to delete the user here
        userRepository.deleteById(userId);
    }

}
