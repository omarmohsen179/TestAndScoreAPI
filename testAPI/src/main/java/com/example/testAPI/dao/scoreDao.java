package com.example.testAPI.dao;

import com.example.testAPI.models.privilege;
import com.example.testAPI.models.score;
import com.example.testAPI.models.user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface scoreDao  extends CrudRepository<score,Long> {
    @Query(value="SELECT * FROM score where userid=?1",nativeQuery=true)
    List<score> finduserscore(Long levelid);
}
