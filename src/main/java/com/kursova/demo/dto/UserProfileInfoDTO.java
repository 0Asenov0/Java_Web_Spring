package com.kursova.demo.dto;

import com.kursova.demo.enums.UserRoleEnum;

import java.util.List;

public class UserProfileInfoDTO {
    Long id;

    String firstName;
    String lastName;

    UserRoleEnum role;
    String email;
    String phone;

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    private List<RentSummaryDTO> summaryCarRent;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<RentSummaryDTO> getSummaryCarRent() {
        return summaryCarRent;
    }

    public void setSummaryCarRent(List<RentSummaryDTO> summaryCarRent) {
        this.summaryCarRent = summaryCarRent;
    }
}
