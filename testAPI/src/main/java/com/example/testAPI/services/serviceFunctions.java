package com.example.testAPI.services;

import com.example.testAPI.dao.*;
import com.example.testAPI.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Service
public class serviceFunctions  implements serviceName{
    @Autowired
    private userDao userD;
    @Autowired
    private privilegeDao privilegeD;
    @Autowired
    private levelDao levelD;
    @Autowired
    private questionDao questionD;
    @Autowired
    private answerDao answerD;
    @Autowired
    private scoreDao scoreD;
    @Autowired
    private usertypeDao usertypeD;

    @Override
    public user Login(String u, String p) {
        user us=userD.loginquery(u,p);
        //set user score
        us.setScore(this.countScore(us.getUserid()));
        //to prevent looping
        us.getLevel().setCreatedby(null);
        return us;
    }

    @Override
    public List<user> getallusers() {
        List<user> us= (List<user>)userD.findAll();
        for(int i=0;i<us.size();i++){
            us.get(i).setScore(this.countScore(us.get(i).getUserid()));
            us.get(i).getLevel().setCreatedby(null);
        }

        return us;
    }

    @Override
    public List<privilege> getallprivilege() {
        return (List<privilege>) privilegeD.findAll();
    }

    @Override
    public List<level> getalllevels() {
        List<level> lv= (List<level>)levelD.findAll();
        for(int i=0;i<lv.size();i++){
            lv.get(i).getCreatedby().setLevel(null);
            //check number of questions in the level
            lv.get(i).setNumberofquestions((long)questionD.findlevelquestion(lv.get(i).getLevelid()).size());
        }
        return lv;
    }

    @Override
    public List<question> getallquestion() {
        return (List<question>) questionD.findAll();
    }

    @Override
    public List<usertype> getallusertypes() {
        return (List<usertype>) usertypeD.findAll();
    }

    //count sum score
    @Override
    public Long countScore(Long userid) {
        Long sum=0L;
        //run query to get scores of user in the score table
        List<score> s=scoreD.finduserscore(userid);
        for(int i=0;i<s.size();i++){
            sum+=s.get(i).getScore();
        }
        return sum;
    }


    @Override
    public user getuserbyid(Long id) {
        user us=userD.finduserbyid(id);
        us.setScore(this.countScore(us.getUserid()));
        us.getLevel().setCreatedby(null);
        return us;
    }

    @Override
    public user getuserbyname(String name) {
        user us=userD.finduserbyname(name);
        us.setScore(this.countScore(us.getUserid()));
        us.getLevel().setCreatedby(null);
        return us;
    }

    @Override
    public privilege getprivilegebyid(Long id) {
        return privilegeD.findprivilagebyid(id);
    }


    @Override
    public level getlevelbyid(Long id) {
        level lv=levelD.findlevelbyid(id);
        lv.getCreatedby().setLevel(null);
        lv.setNumberofquestions((long)questionD.findlevelquestion(lv.getLevelid()).size());
        return lv;
    }

    @Override
    public level getlevelbyname(String name) {
        level lv=levelD.findlevelbyname(name);
        lv.getCreatedby().setLevel(null);
        lv.setNumberofquestions((long)questionD.findlevelquestion(lv.getLevelid()).size());

        return lv;
    }

    @Override
    public question getquestionbyid(Long id) {
        question q=  questionD.findquestionbyid(id);
        q.setAnswers(answerD.findquestionansers(q.getQuestionid()));
        return q;
    }

    @Override
    public List<question> getlevelquestions(Long id) {
        List<question> q=questionD.findlevelquestion(id);
        for(int i=0;i<q.size();i++){
            q.get(i).setAnswers(answerD.findquestionansers(q.get(i).getQuestionid()));
            q.get(i).getCreatedby().setLevel(null);
        }
        return q;
    }

    @Override
    public user seruserlevel(user u) {
        if(levelD.findlevelbyid(u.getLevel().getLevelid())==null){
            return null;
        }
        if(userD.finduserbyid(u.getUserid())==null){
            return null;
        }

        user main=userD.finduserbyid(u.getUserid());
        level s=levelD.findlevelbyid(u.getLevel().getLevelid());
        user  ux=userD.save(main);
        ux.getLevel().setCreatedby(null);
        return ux;
    }


    @Override
    public user insertuser(user u) {
        if(userD.finduserbyname(u.getUsername())!=null||
                usertypeD.findusertypebyid(u.getUsertype().getUsertypeid())==null){
            return null;
        }
        u.setUsertype(usertypeD.findusertypebyid(u.getUsertype().getUsertypeid()));

        return userD.save(u);

    }

    @Override
    public usertype insertusertype(usertype u) {
        //check if privilege is located
        if(privilegeD.findprivilagebyid(u.getPrivilege().getPrivilegeid())==null){
            return null;
        }
        u.setPrivilege(privilegeD.findprivilagebyid(u.getPrivilege().getPrivilegeid()));
        return usertypeD.save(u);
    }

    @Override
    public privilege insertprivilage(privilege p) {
        return privilegeD.save(p);
    }

    @Override
    public level insertlevel(level l) {
        l.setNumberofquestions(0L);
        if(userD.finduserbyid(l.getCreatedby().getUserid())==null){
            return null;
        }

        if(l.getName()==null){
            return null;
        }
        user m=userD.finduserbyid(l.getCreatedby().getUserid());
        l.setCreatedby(m);
        return levelD.save(l);
    }

    @Override
    public question insertquestion(question q) {
        if(levelD.findlevelbyid(q.getLevelid())==null){
            return null;
        }
       if(q.getAnswers()==null){
            return null;
        }
       if(userD.finduserbyid(q.getCreatedby().getUserid())==null){
            return null;
        }
        List<answer> an=q.getAnswers();
       q.setAnswers(null);
         questionD.save(q);

       for(int i=0;i<an.size();i++){
            an.get(i).setQuestionid(q.getQuestionid());
        }
        answerD.saveAll(an);
        return  q;
    }

    @Override
    public score insertscore(score s) {
        if(userD.finduserbyid(s.getUserid())==null){
            return null;
        }
        if(s.getQuestionid()!=null){
            if(questionD.findquestionbyid(s.getQuestionid())==null){
                return null;
            }
        }
        return scoreD.save(s);
    }

    @Override
    public user updateuser(user u) {
        user main=userD.finduserbyid(u.getUserid());
        if(main!=null){
            if(!main.getUsername().equals(u.getUsername())&&userD.finduserbyname(u.getUsername())==null){
                main.setUsername(u.getUsername());
            }
            if(!main.getName().equals(u.getName())||
                    !main.getEmail().equals(u.getEmail())||
                    !main.getTitle().equals(u.getTitle())
            ){
                main.setTitle(u.getTitle());
                main.setName(u.getName());
                main.setEmail(u.getEmail());
            }
            if(!main.getPassword().equals(u.getPassword())&&
                    u.getPassword().length()>5){
                main.setPassword(u.getPassword());
            }
            user us= userD.save(main);
            us.setScore(this.countScore(us.getUserid()));
            us.getLevel().setCreatedby(null);
            return us;
        }
        else{
            return null;
        }
    }

    @Override
    public usertype updateusertype(usertype u) {
        usertype main=usertypeD.findusertypebyid(u.getUsertypeid());
        if(privilegeD.findprivilagebyid(u.getPrivilege().getPrivilegeid())==null){
            return null;
        }else if(!u.getPrivilege().getPrivilegeid().equals(main.getPrivilege().getPrivilegeid())){
            main.setPrivilege(privilegeD.findprivilagebyid(u.getPrivilege().getPrivilegeid()));
        }
        if(!main.getUsertype().equals(u.getUsertype())){
            main.setUsertype(u.getUsertype());
        }
        return usertypeD.save(main);
    }

    @Override
    public privilege updateprivilege(privilege p) {
        privilege main=privilegeD.findprivilagebyid(p.getPrivilegeid());
        if(main!=null){
            if(!main.getPrivilegelevel().equals(p.getPrivilegelevel())){
                main.setPrivilegelevel(p.getPrivilegelevel());
            }
            return privilegeD.save(main);
        }
        else{
            return null;
        }
    }

    @Override
    public level updatelevel(level l) {
        level main=levelD.findlevelbyid(l.getLevelid());
        if(main==null){
            return null;
        }
        if(userD.finduserbyid(l.getCreatedby().getUserid())==null){
            return null;
        }
      if(l.getLeveltype()==null||l.getName()==null){
            return null;
        }
        if(!l.getLeveltype().equals(main.getLeveltype())
        || !l.getName().equals(main.getName())){
            main.setLeveltype(l.getLeveltype());
            main.setName(l.getName());
        }
        level lv= levelD.save(main);
        lv.getCreatedby().setLevel(null);
        return lv;
    }

    @Override
    public question updatequestion(question q) {
        question main=questionD.findquestionbyid(q.getQuestionid());
        if(main==null){
            return null;
        }
        if(userD.finduserbyid(q.getCreatedby().getUserid())==null){
            return null;
        }
        List<answer> mainansers=answerD.findquestionansers(q.getQuestionid());
        List<answer> updateansers=q.getAnswers();
        if(!q.getQuestion().equals(main.getQuestion())
        || !q.getScore().equals(main.getScore())
        || !q.getQuestionType().equals(main.getQuestionType())
        ){
            main.setQuestion(q.getQuestion());
            main.setScore(q.getScore());
            main.setQuestionType(q.getQuestionType());
            main.setAnswers(null);
            questionD.save(main);
        }
        if(updateansers!=null){
           if(mainansers.size()<updateansers.size()){
               //update if add answer
                    for(int i=0;i<updateansers.size();i++){
                        int checker =0;
                        for(int j=0;j<mainansers.size();j++){

                            if(mainansers.get(j).getAnswerid().equals(updateansers.get(i).getAnswerid())){
                                if(mainansers.get(j).getCorrect()!= updateansers.get(i).getCorrect() || !mainansers.get(j).getAnswer().equals(updateansers.get(i).getAnswer())
                                ){
                                    mainansers.get(j).setAnswer(updateansers.get(i).getAnswer());
                                    mainansers.get(j).setCorrect(updateansers.get(i).getCorrect());
                                    checker=1;
                                    answerD.save(mainansers.get(j));
                                }
                            }
                           if(checker==0){
                               answerD.save(updateansers.get(i));
                           }
                        }
                    }
           }
           else if(mainansers.size()>updateansers.size()){
               //update if delete answer
              for(int i=0;i<mainansers.size();i++){
                  int checker=0;
                  for(int j=0;j<updateansers.size();j++){
                      if(mainansers.get(i).getAnswerid()==
                      updateansers.get(j).getAnswerid()){
                          if(mainansers.get(i).getCorrect()!=
                                  updateansers.get(j).getCorrect()
                                  || !mainansers.get(i).getAnswer().equals(updateansers.get(j).getAnswer())
                          ){
                              mainansers.get(i).setAnswer(updateansers.get(j).getAnswer());
                              mainansers.get(i).setCorrect(updateansers.get(j).getCorrect());
                              answerD.save(mainansers.get(i));
                          }
                          checker=1;
                      }

                  }
                  if(checker==0){
                      answerD.deleteById(mainansers.get(i).getAnswerid());
                  }
              }
           }
           else{
              //update in the same amount of answers
               for(int i=0;i<mainansers.size();i++){

                 for(int j=0;j<updateansers.size();j++){

                   if(mainansers.get(i).getAnswerid().equals(updateansers.get(j).getAnswerid())){
                       if(mainansers.get(i).getCorrect()!=updateansers.get(j).getCorrect()
                       || !mainansers.get(i).getAnswer().equals(updateansers.get(j).getAnswer())
                       ){
                             mainansers.get(i).setAnswer(updateansers.get(j).getAnswer());
                             mainansers.get(i).setCorrect(updateansers.get(j).getCorrect());
                             answerD.save(mainansers.get(i));

                          }
                        }
                   }
               }
           }

        }

        return q;
    }

    @Override
    public String deleteusertype(Long id) {
        if(usertypeD.findusertypebyid(id)==null){
            return "id is not valid";
        }
        usertypeD.deleteById(id);
        return " usertype been deleted successfully ";
    }

    @Override
    public String deleteuser(Long id) {
        if(userD.finduserbyid(id)==null){
            return "id is not valid";
        }
        userD.deleteById(id);

        return " user been deleted successfully ";
    }

    @Override
    public String deleteprivilege(Long id) {
        if(privilegeD.findprivilagebyid(id)==null){
            return "id is not valid";
        }
        privilegeD.deleteById(id);

        return " privilege been deleted successfully ";
    }

    @Override
    public String deletequestion(Long id) {
        if(questionD.findquestionbyid(id)==null){
            return "id is not valid";
        }
        questionD.deleteById(id);
        List<answer> ans=answerD.findquestionansers(id);
        for(int i=0;i<ans.size();i++){
            answerD.deleteById(ans.get(i).getAnswerid());
        }
        return " question been deleted successfully ";
    }

    @Override
    public String deletelevel(Long id) {
        if(levelD.findlevelbyid(id)==null){
            return "id is not valid";
        }
        levelD.deleteById(id);
        List<question> ans=questionD.findlevelquestion(id);
        for(int i=0;i<ans.size();i++){
            questionD.deleteById(ans.get(i).getQuestionid());
        }
        return " level been deleted successfully ";
    }


}
