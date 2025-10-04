package com.sarita.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarita.onlinequiz.entities.User;
import com.sarita.onlinequiz.enums.UserRole;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByRole(UserRole role);

    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);
}
