package com.sarita.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarita.onlinequiz.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>{

}
