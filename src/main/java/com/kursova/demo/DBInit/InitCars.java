package com.kursova.demo.DBInit;

import com.kursova.demo.models.CarEntity;
import com.kursova.demo.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCars implements CommandLineRunner {

    private final CarRepository carRepository;


    public InitCars(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (carRepository.count() == 0) {
            initFerrari();
        }
    }
/*carMaker;
carModel;
 hp;
dailyRentPrice
acceleration;
catchPhrase;
 introduction;
description;
mainPhotoUrl;
secondary_url1
secondaryUrl2;
secondaryUrl3;
secondaryUrl4;*/
private void initFerrari(){
  initCar("Ferrari",
          "480",
          "500",
          "980",
          "3.1",
          "Maranello's glorious V12",
          "Everyone thinks red cars are fast.This brand is the reason why.Feel the prancing horse beneath your seat.Try the oustanding Ferrari 488 Pista.",
           "Introducing the <b>Ferrari 488 Spider</b> rental Miami,",
          "images/ferrari.webp"
          );

}
 private void initCar(String name,String ...params){
     CarEntity carEntity = new CarEntity();
     carEntity.setCarMaker(name);
     carEntity.setCarModel(params[0]);
     carEntity.setHp(Integer.parseInt(params[1]));
     carEntity.setDailyRentPrice(Double.valueOf(params[2]));
     carEntity.setAcceleration(Double.valueOf(params[3]));
     carEntity.setCatchPhrase(params[4]);
     carEntity.setIntroduction(params[5]);
     carEntity.setDescription(params[6]);
     carEntity.setMainPhotoUrl(params[7]);
     carRepository.save(carEntity);


 }
}
