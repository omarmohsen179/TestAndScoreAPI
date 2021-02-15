package com.example.testAPI.models;


import javax.persistence.*;

@Entity
@Table(name = "usertype")
public class usertype {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usertypeid;
    @Column(name="usertype",nullable = false)
    private String usertype;
    @ManyToOne
    @JoinColumn(name = "privilegeid")
    private privilege privilege;

    public Long getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(Long usertypeid) {
        this.usertypeid = usertypeid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public com.example.testAPI.models.privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(com.example.testAPI.models.privilege privilege) {
        this.privilege = privilege;
    }
}
