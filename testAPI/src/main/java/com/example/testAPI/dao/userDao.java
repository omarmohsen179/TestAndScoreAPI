package com.example.testAPI.dao;

import com.example.testAPI.models.user;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userDao  extends CrudRepository<user,Long> {
    @Query(value="SELECT * FROM user where userid=?1",nativeQuery=true)
    user finduserbyid(Long userid);
    @Query(value="SELECT * FROM user where username=?1 ",nativeQuery=true)
    user finduserbyname(String username);
    @Query(value="SELECT * FROM user where username=?1 AND password=?2",nativeQuery=true)
    user loginquery(String username,String password);
}
