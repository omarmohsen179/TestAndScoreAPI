package com.example.testAPI.dao;

import com.example.testAPI.models.level;
import com.example.testAPI.models.privilege;
import com.example.testAPI.models.question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface questionDao extends CrudRepository<question,Long> {
  @Query(value="SELECT * FROM question where questionid=?1",nativeQuery=true)
    question findquestionbyid(Long questionid);
    @Query(value="SELECT * FROM question where level_id=?1",nativeQuery=true)
    List<question> findlevelquestion(Long questionid);
}
