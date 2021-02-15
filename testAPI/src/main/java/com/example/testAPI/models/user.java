package com.example.testAPI.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")

public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="score",nullable = false)
    private Long score;
    @ManyToOne
    @JoinColumn(name = "usertypeid")
    private usertype usertype;

    @ManyToOne
    @JoinColumn(name = "levelid")
    private level level;

    public com.example.testAPI.models.usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(com.example.testAPI.models.usertype usertype) {
        this.usertype = usertype;
    }

    public com.example.testAPI.models.level getLevel() {
        return level;
    }

    public void setLevel(com.example.testAPI.models.level level) {
        this.level = level;
    }


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}
