package com.example.testAPI.dao;

import com.example.testAPI.models.level;
import com.example.testAPI.models.privilege;
import com.example.testAPI.models.user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface levelDao extends CrudRepository<level,Long> {
    @Query(value="SELECT * FROM level where levelid=?1",nativeQuery=true)
    level findlevelbyid(Long userid);
    @Query(value="SELECT * FROM level where name=?1 ",nativeQuery=true)
    level findlevelbyname(String username);


}
