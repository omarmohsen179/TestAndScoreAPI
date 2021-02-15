package com.example.testAPI.services;

import com.example.testAPI.models.*;

import java.util.List;

public interface serviceName {
    user Login (String u,String p);

    List<user> getallusers();
    List<privilege> getallprivilege();
    List<level> getalllevels();
    List<question> getallquestion();
    List<usertype> getallusertypes();

    Long countScore(Long userid);
    user getuserbyid(Long id);
    user getuserbyname(String name);
    privilege getprivilegebyid(Long id);
    level getlevelbyid(Long id);
    level getlevelbyname(String name);
    question getquestionbyid(Long id);
    List<question> getlevelquestions(Long id);


    user seruserlevel(user u);
    user insertuser(user u);
    usertype insertusertype(usertype u);
    privilege insertprivilage(privilege p);
    level insertlevel(level l);
    question insertquestion(question q);
    score insertscore(score s);


    user updateuser(user u);
    usertype updateusertype(usertype u);
    privilege updateprivilege(privilege p);
    level updatelevel(level l);
    question updatequestion(question q);

    String deleteusertype(Long id);
    String deleteuser(Long id);
    String deleteprivilege(Long id);
    String deletequestion(Long id);
    String deletelevel(Long id);
}
