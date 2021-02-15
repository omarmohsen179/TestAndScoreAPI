package com.example.testAPI.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionid;
    @Column(name="question",nullable = false)
    private String question;
    @Column(name="questionType",nullable = false)
    private String questionType;
    @Column(name="score",nullable = false)
    private Long score;
    @Column(name="level_id")
    private Long levelid;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    private user createdby;
    @OneToMany
    List<answer> answers;

    public Long getLevelid() {
        return levelid;
    }

    public void setLevelid(Long levelid) {
        this.levelid = levelid;
    }

    public List<answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<answer> answers) {
        this.answers = answers;
    }

    public user getCreatedby() {
        return createdby;
    }

    public void setCreatedby(user createdby) {
        this.createdby = createdby;
    }

    public Long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}
