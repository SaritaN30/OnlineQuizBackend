package com.sarita.onlinequiz.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarita.onlinequiz.entities.User;
import com.sarita.onlinequiz.enums.UserRole;
import com.sarita.onlinequiz.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void createAdminUser(){
       
        User adminUser = userRepository.findByRole(UserRole.ADMIN);
        if(adminUser == null) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("useradmin@gmail.com");
            user.setRole(UserRole.ADMIN);
            user.setPassword("admin");

            userRepository.save(user);     
           }
    }

    public Boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }

    public User createUser(User user){
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    public User login(User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())){
            return optionalUser.get();
        }
        return null;
    }
}
