package com.sarita.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarita.onlinequiz.entities.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}
