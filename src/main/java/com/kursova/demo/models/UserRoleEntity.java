package com.kursova.demo.models;

import com.kursova.demo.enums.UserRoleEnum;
import jakarta.persistence.*;
@Entity
@Table( name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
    public UserRoleEnum getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}