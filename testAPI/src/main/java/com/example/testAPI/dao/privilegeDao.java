package com.example.testAPI.dao;

import com.example.testAPI.models.privilege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface privilegeDao extends CrudRepository<privilege,Long> {
    @Query(value="SELECT * FROM privilege where privilegeid=?1",nativeQuery=true)
    privilege findprivilagebyid(Long levelid);
}
