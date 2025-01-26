package com.kursova.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserLoginDto{
@NotNull
@Length(min = 3 , max = 20,message = "wrong one !!")
 String email;
@NotNull
@Length(min = 2 , max = 20)
String password;

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }
}
