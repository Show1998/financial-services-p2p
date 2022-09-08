package com.cpp.auth.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "u_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "phone")
    public String phone;

    @Column(name = "login_password")
    public String loginPassword;

    @Column(name = "name")
    public String name;

    @Column(name = "id_card")
    public String idCard;

    @Column(name = "add_time")
    public Date addTime;

    @Column(name = "last_login_time")
    public Date lastLoginTime;

    @Column(name = "header_image")
    public String headerImage;
}
