package com.example.testAPI.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "level")

public class level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long levelid;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="numberofquestions",nullable = false)
    private Long numberofquestions;
    @Column(name="leveltype",nullable = false)
    private String leveltype;

    /*@OneToMany
    @JoinColumn(name = "questionid", insertable = true, updatable = true)
    List<question> questions;*/
    @ManyToOne
    @JoinColumn(name="userid" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private user createdby;

    public user getCreatedby() {
        return createdby;
    }

    public void setCreatedby(user createdby) {
        this.createdby = createdby;
    }

    public Long getLevelid() {
        return levelid;
    }

    public void setLevelid(Long levelid) {
        this.levelid = levelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberofquestions() {
        return numberofquestions;
    }

    public void setNumberofquestions(Long numberofquestions) {
        this.numberofquestions = numberofquestions;
    }

    public String getLeveltype() {
        return leveltype;
    }

    public void setLeveltype(String leveltype) {
        this.leveltype = leveltype;
    }

 /*   public List<question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<question> questions) {
        this.questions = questions;
    }*/
}
