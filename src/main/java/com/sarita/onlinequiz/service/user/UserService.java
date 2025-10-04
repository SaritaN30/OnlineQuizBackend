package com.sarita.onlinequiz.service.user;

import com.sarita.onlinequiz.entities.User;

public interface UserService {

    User createUser(User user);

    Boolean hasUserWithEmail(String email);

    User login(User user);
}
