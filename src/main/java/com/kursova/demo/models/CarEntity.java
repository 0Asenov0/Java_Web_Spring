package com.kursova.demo.models;

import com.kursova.demo.enums.PowertrainEnum;
import com.kursova.demo.enums.TypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    private String carMaker;
    private String carModel;
    private Integer hp;
    private Double dailyRentPrice;
    private Double acceleration;
    private String catchPhrase;
    private String  introduction;
    private String description;
    private String mainPhotoUrl;
    private String secondary_url1;
    private String secondaryUrl2;
    private String secondaryUrl3;
    private String secondaryUrl4;

    @Enumerated(EnumType.STRING)
    private PowertrainEnum powerTrain;
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
        @ManyToOne
    private UserEntity userRent;

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

    public UserEntity getUserEntity() {
        return userRent;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userRent = userEntity;
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

    public UserEntity getUserRent() {
        return userRent;
    }

    public void setUserRent(UserEntity userRent) {
        this.userRent = userRent;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    public String getSecondary_url1() {
        return secondary_url1;
    }

    public void setSecondary_url1(String secondary_url1) {
        this.secondary_url1 = secondary_url1;
    }

    public String getSecondaryUrl2() {
        return secondaryUrl2;
    }

    public void setSecondaryUrl2(String secondaryUrl2) {
        this.secondaryUrl2 = secondaryUrl2;
    }

    public String getSecondaryUrl3() {
        return secondaryUrl3;
    }

    public void setSecondaryUrl3(String secondaryUrl3) {
        this.secondaryUrl3 = secondaryUrl3;
    }

    public String getSecondaryUrl4() {
        return secondaryUrl4;
    }

    public void setSecondaryUrl4(String secondaryUrl4) {
        this.secondaryUrl4 = secondaryUrl4;
    }
}
