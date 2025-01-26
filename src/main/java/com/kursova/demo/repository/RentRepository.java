package com.kursova.demo.repository;

import com.kursova.demo.dto.RentDto;
import com.kursova.demo.models.CarEntity;
import com.kursova.demo.models.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<RentEntity,Long> {

    boolean findByStartDate(Date date);
    boolean findByEndDate(Date date);

    List<RentEntity> findByUserId(Long id);

    @Query("select r.userId from RentEntity as r where r.startDate>=(:query1) and r.endDate<=(:query2)")
    List<Long> searchRented(Date query1,Date query2);
}
