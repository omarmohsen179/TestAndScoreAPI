package com.example.testAPI.dao;


import com.example.testAPI.models.answer;
import com.example.testAPI.models.question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface answerDao extends CrudRepository<answer,Long> {
    @Query(value="SELECT * FROM answer where question_id=?1",nativeQuery=true)
    List<answer> findquestionansers(Long questionid);
}
