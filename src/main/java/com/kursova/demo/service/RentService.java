package com.kursova.demo.service;

import com.kursova.demo.dto.RentDto;

public interface RentService {
    boolean addRentRequest(RentDto rentDto,Long id);
}
