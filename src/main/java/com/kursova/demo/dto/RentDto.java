package com.kursova.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RentDto {
    private String username;
    private String contact;
    @DateTimeFormat(pattern =  "yyyy-mm-dd")
    private  Date startDate;
    @DateTimeFormat(pattern =  "yyyy-mm-dd")
    private Date endDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
