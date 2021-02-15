package com.example.testAPI.models;


import javax.persistence.*;

@Entity
@Table(name = "score")
public class score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scoreid;
    @Column(name="userid",nullable = false)
    private Long userid;
    @Column(name="questionid")
    private Long questionid;
    @Column(name="score",nullable = false)
    private Long score;

    public Long getScoreid() {
        return scoreid;
    }

    public void setScoreid(Long scoreid) {
        this.scoreid = scoreid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
