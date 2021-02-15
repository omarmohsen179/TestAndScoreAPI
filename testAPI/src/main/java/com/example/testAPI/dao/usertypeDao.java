package com.example.testAPI.dao;

import com.example.testAPI.models.user;
import com.example.testAPI.models.usertype;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface usertypeDao   extends CrudRepository<usertype,Long> {
    @Query(value="SELECT * FROM usertype where usertypeid=?1",nativeQuery=true)
    usertype findusertypebyid(Long userid);
}
