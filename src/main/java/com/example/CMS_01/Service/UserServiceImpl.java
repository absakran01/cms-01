package com.example.CMS_01.Service;

import java.util.List;
import java.util.Optional;

import com.example.CMS_01.Entity.User;
import com.example.CMS_01.POJO.UserWrapper;
import com.example.CMS_01.Repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
//        List<Trophy> list = new ArrayList<Trophy>();
//        list.add(new Trophy("SWE", 10,30,user));
//        list.add(new Trophy("Cpp", 10,30,user));
//        list.add(new Trophy("Algorithms", 10,30,user));
//        for (Trophy x:list
//             ) {
//            trophyRepository.save(x);
//        }
        user.setAlgorithmsQuiz("");

        user.setCppQuiz("");

        user.setSWEQuiz("");

        return userRepository.save(user);
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
    public boolean checkUserAndSecurityInfo(UserWrapper userWrapper) {
        if((!userWrapper.getUser().getUsername().equals(getCurrentUser().getUsername())))throw new UsernameNotFoundException("");
        if(!(bCryptPasswordEncoder.matches(userWrapper.getUser().getPassword(),user.getPassword()))) throw new UsernameNotFoundException("");
        if(userRepository.findByUsername(userWrapper.getNewCred().getUsername()).isPresent()) throw new UsernameNotFoundException("");
        return true;

    }


}
