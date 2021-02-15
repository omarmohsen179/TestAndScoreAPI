package com.example.testAPI.models;


import javax.persistence.*;

@Entity
@Table(name = "answer")
public class answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerid;
    @Column(name="answer",nullable = false)
    private String answer;
    @Column(name="correct",nullable = false)
    private Boolean correct;
    @Column(name="question_id",nullable = false)
    private Long questionid;

    public Long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }

    public Long getAnswerid() {
        return answerid;
    }

    public void setAnswerid(Long answerid) {
        this.answerid = answerid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
