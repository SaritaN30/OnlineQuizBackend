package com.sarita.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarita.onlinequiz.entities.User;
import com.sarita.onlinequiz.service.user.UserService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signupUser(@RequestBody User user){

        if(userService.hasUserWithEmail(user.getEmail())){
            return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
        }

        User createdUser = userService.createUser(user);
        if(createdUser == null){
            return new ResponseEntity<>("User not created!! Try Sometime later",HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User loginUser = userService.login(user);
        if(loginUser == null){
            return new ResponseEntity<>("Invalid Credentials" , HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(loginUser,HttpStatus.OK);
    }


}
