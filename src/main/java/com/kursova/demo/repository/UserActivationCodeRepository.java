package com.kursova.demo.repository;

import com.kursova.demo.models.UserActivationCodeEntity;
import com.kursova.demo.service.UserActivationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivationCodeRepository extends JpaRepository<UserActivationCodeEntity,Long> {
  UserActivationCodeEntity findByActivationCode(String activationCode);
}
