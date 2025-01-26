package com.kursova.demo.dto;

import com.kursova.demo.enums.PowertrainEnum;
import com.kursova.demo.enums.TypeEnum;

public class CarSummaryDto {
    private Long id;
    private String carMaker;
    private Integer hp;
    private Double dailyRentPrice;
    private Double acceleration;
    private String carModel;
    private String url;
    private PowertrainEnum powerTrain;
    private TypeEnum type;
    private String catchPhrase;
    private String  introduction;
    private String description;
    private UserLoginDto userLoginDto;
    private String mainPhotoUrl;
    private String url1;
    private String url2;
    private String url3;
    private String url4;

    private String descriptionP1;
    private String descriptionP2;
    private String descriptionP3;

    public String getDescriptionP2() {
        return descriptionP2;
    }

    public void setDescriptionP2(String descriptionP2) {
        this.descriptionP2 = descriptionP2;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getDescriptionP1() {
        return descriptionP1;
    }

    public void setDescriptionP1(String descriptionP1) {
        this.descriptionP1 = descriptionP1;
    }

    public String getDescriptionP3() {
        return descriptionP3;
    }

    public void setDescriptionP3(String descriptionP3) {
        this.descriptionP3 = descriptionP3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public void setCarMaker(String carMaker) {
        this.carMaker = carMaker;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Double getDailyRentPrice() {
        return dailyRentPrice;
    }

    public void setDailyRentPrice(Double dailyRentPrice) {
        this.dailyRentPrice = dailyRentPrice;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PowertrainEnum getPowerTrain() {
        return powerTrain;
    }

    public void setPowerTrain(PowertrainEnum powerTrain) {
        this.powerTrain = powerTrain;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserLoginDto getUserLoginDto() {
        return userLoginDto;
    }

    public void setUserLoginDto(UserLoginDto userLoginDto) {
        this.userLoginDto = userLoginDto;
    }
}


