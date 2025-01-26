package com.kursova.demo.models;

import com.kursova.demo.enums.TypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name="models")
public class ModelEntity extends BaseEntity {

    private String model;

    @Enumerated(EnumType.STRING)
    private TypeEnum category;

    @ManyToOne
    private BrandEntity brandsEntity;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public TypeEnum getCategory() {
        return category;
    }

    public void setCategory(TypeEnum category) {
        this.category = category;
    }

    public BrandEntity getBrand() {
        return brandsEntity;
    }

    public BrandEntity getBrandsEntity() {
        return brandsEntity;
    }

    public void setBrandsEntity(BrandEntity brandsEntity) {
        this.brandsEntity = brandsEntity;
    }
}