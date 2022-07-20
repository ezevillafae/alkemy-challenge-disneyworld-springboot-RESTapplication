package com.alkemy.disneyworldspringbootapplication.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;
    private String password;


}

