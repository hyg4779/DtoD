package com.ssafy.dtod.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "USER_GRADE")
    private int userGrade;

    @Column(name = "USER_TECHSTACK")
    private int userTechstack;
}