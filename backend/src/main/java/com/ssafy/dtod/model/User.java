package com.ssafy.dtod.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    private String email;

    private String username;

    private String password;
}