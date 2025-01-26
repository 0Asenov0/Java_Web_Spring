package com.kursova.demo.repository;

import com.kursova.demo.enums.UserRoleEnum;
import com.kursova.demo.models.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {

}
