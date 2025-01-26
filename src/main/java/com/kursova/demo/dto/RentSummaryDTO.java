package com.kursova.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RentSummaryDTO {

    private String carMaker;
    private String carModel;
    @DateTimeFormat(pattern =  "yyyy-mm-dd")
    private  Date startDate;
    @DateTimeFormat(pattern =  "yyyy-mm-dd")
    private Date endDate;

    public String getCarMaker() {
        return carMaker;
    }

    public void setCarMaker(String carMaker) {
        this.carMaker = carMaker;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
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
}
