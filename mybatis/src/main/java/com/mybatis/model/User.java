package com.mybatis.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    BigInteger id;

    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "user_status")
    Byte userStatus;

    @Column(name = "des")
    String des;
}
