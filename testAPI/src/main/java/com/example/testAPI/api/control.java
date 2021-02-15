package com.example.testAPI.api;

import com.example.testAPI.models.*;
import com.example.testAPI.services.serviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/")
public class control {


    @Autowired
    private serviceName serviceName;


    @GetMapping("/")
    public String home()
    {
        return "my server";
    }
    @GetMapping("/login/{username}/{password}")
    public user login(@PathVariable  String username, @PathVariable  String password)
    {
        return serviceName.Login(username,password);
    }

    @GetMapping("/AllUsers")
    public List<user> allusers()
    {
        return serviceName.getallusers();
    }

    @GetMapping("/AllPrivilege")
    public List<privilege> allprivilage()
    {
        return serviceName.getallprivilege();
    }

    @GetMapping("/AllLevels")
    public List<level> alllevels()
    {
        return (List<level>)serviceName.getalllevels();
    }
    @GetMapping("/AllQuestion")
    public List<question> allquestions()
    {
        return (List<question>)serviceName.getallquestion();
    }


    @GetMapping("/UserByUsername/{username}")
    public user userbyname(@PathVariable  String username)
    {
        return serviceName.getuserbyname(username);
    }
    @GetMapping("/UserById/{id}")
    public user userbyid(@PathVariable  Long id)
    {
        return serviceName.getuserbyid(id);
    }

    @GetMapping("/PrivilegeById/{id}")
    public privilege Privilegebyid(@PathVariable  Long id)
    {
        return serviceName.getprivilegebyid(id);
    }
    @GetMapping("/LevelById/{id}")
    public level levelbyid(@PathVariable Long id)
    {
        return serviceName.getlevelbyid(id);
    }
    @GetMapping("/LevelQuestion/{id}")
    public List<question> levelqiestion(@PathVariable Long id)
    {
        return serviceName.getlevelquestions(id);
    }
    @GetMapping("/LevelByName/{name}")
    public level levelbyname(@PathVariable String name)
    {
        return serviceName.getlevelbyname(name);
    }
    @GetMapping("/QuestionById/{id}")
    public question questionbyname(@PathVariable Long id)
    {
        return serviceName.getquestionbyid(id);
    }

    @PostMapping("/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public user insertuser(@RequestBody user us)
    {
        us.setScore(0L);
        if(us.getPassword()!=null
                &&us.getName()!=null
                &&us.getUsername()!=null
                &&us.getTitle()!=null
                && us.getUsertype()!=null
        )
            return serviceName.insertuser(us);
        else{
            return null;
        }

    }
    @PostMapping("/addUserType")
    @ResponseStatus(HttpStatus.CREATED)
    public usertype insertusetype(@RequestBody usertype us)
    {

        if(us.getUsertype()!=null
                &&us.getPrivilege()!=null
        )
            return serviceName.insertusertype(us);
        else{
            return null;
        }

    }
    @PostMapping("/addPrivilege")
    @ResponseStatus(HttpStatus.CREATED)
    public privilege insertprivilege(@RequestBody privilege us)
    {
        if(us.getPrivilegelevel()!=null)
            return serviceName.insertprivilage(us);
        else{
            return null;
        }
    }
    @PostMapping("/setUserLevel")
    @ResponseStatus(HttpStatus.CREATED)
    public user setuserlevel(@RequestBody user u)
    {

        if(u.getUserid()!=null&&u.getLevel().getLevelid()!=null
        )

            return serviceName.seruserlevel(u);
        else{
            return null;
        }

    }
    @PostMapping("/addScore")
    @ResponseStatus(HttpStatus.CREATED)
    public score insertscore(@RequestBody score s)
    {

        if(s.getScore()!=null&s.getUserid()!=null
        )
            return serviceName.insertscore(s);
        else{
            return null;
        }

    }
    @PostMapping("/addLevel")
    @ResponseStatus(HttpStatus.CREATED)
    public level insertlevel(@RequestBody level l)
    {

        if(l.getName()!=null&&l.getLeveltype()!=null
           &&l.getCreatedby()!=null
        )
            return serviceName.insertlevel(l);
        else{
            return null;
        }

    }
    @PostMapping("/addQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public question insertquestion(@RequestBody question q)
    {

        if(q.getQuestion()!=null&&q.getLevelid()!=null
        )
            return serviceName.insertquestion(q);

        else{
            return null;
        }

    }





    @PutMapping("/updateUser")
    @ResponseStatus(HttpStatus.CREATED)
    public user updateuser(@RequestBody user user)
    {
        if(     user.getUsername()!=null&&
                user.getPassword()!=null&&
                user.getName()!=null&&
                user.getTitle()!=null&&
                user.getUserid()!=null
        ) {
            return serviceName.updateuser(user);
        }
        else{
            return null;
        }
    }
    @PutMapping("/updatePrivilege")
    @ResponseStatus(HttpStatus.CREATED)
    public privilege updateprivilege(@RequestBody privilege us)
    {
        if(us.getPrivilegelevel()!=null

                &&us.getPrivilegeid()!=null
        )
            return serviceName.updateprivilege(us);
        else{
            return null;
        }

    }
    @PutMapping("/updateLevel")
    @ResponseStatus(HttpStatus.CREATED)
    public level updateprivilege(@RequestBody level l)
    {
        if(l.getLevelid()!=null
                &&l.getLeveltype()!=null
                &&l.getName()!=null
                &&l.getCreatedby()!=null
        )
            return serviceName.updatelevel(l);
        else{
            return null;
        }

    }
    @PutMapping("/updateUserType")
    @ResponseStatus(HttpStatus.CREATED)
    public usertype updateusertype(@RequestBody usertype l)
    {
        if(l.getUsertype()!=null
                &&l.getPrivilege()!=null
                &&l.getUsertypeid()!=null
        )
            return serviceName.updateusertype(l);
        else{
            return null;
        }

    }
    @PutMapping("/updateQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public question updatequestion(@RequestBody question q)
    {
        if(q.getLevelid()!=null
                &&q.getQuestion()!=null
                &&q.getQuestionType()!=null
                &&q.getCreatedby()!=null
        )
            return serviceName.updatequestion(q);
        else{
            return null;
        }

    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteuser(@PathVariable Long id) {
        return serviceName.deleteuser(id);
    }
    @DeleteMapping("/deletePrivilege/{id}")
    public String deleteprivilege(@PathVariable Long id) {
        return serviceName.deleteprivilege(id);
    }
    @DeleteMapping("/deleteQuestion/{id}")
    public String deletequestion(@PathVariable Long id) {
        return serviceName.deletequestion(id);
    }
    @DeleteMapping("/deleteLevel/{id}")
    public String deletelevel(@PathVariable Long id) {
        return serviceName.deletelevel(id);
    }


}
