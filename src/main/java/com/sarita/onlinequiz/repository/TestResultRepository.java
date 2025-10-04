package com.sarita.onlinequiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarita.onlinequiz.entities.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult,Long>{

    List<TestResult> findAllByUserId(Long userId);

}
