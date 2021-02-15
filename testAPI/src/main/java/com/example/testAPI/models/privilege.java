package com.example.testAPI.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "privilege")
public class privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long privilegeid;
    @Column(name="privilegelevel",nullable = false)
    private Long privilegelevel;

    public Long getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Long privilegeid) {
        this.privilegeid = privilegeid;
    }
    public Long getPrivilegelevel() {
        return privilegelevel;
    }

    public void setPrivilegelevel(Long privilegelevel) {
        this.privilegelevel = privilegelevel;
    }
}
