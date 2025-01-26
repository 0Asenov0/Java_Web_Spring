package com.kursova.demo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;

@Configuration
public class MaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        String requestUrl = request.getRequestURI();

        if(!requestUrl.equals("/maintenance")){
            LocalTime timeNow  = LocalTime.now();

            if(timeNow.getHour() == 11 && timeNow.getMinute() ==02){
                response.sendRedirect("/maintenance");
                return false;
            }
        }

        return HandlerInterceptor.super.preHandle(request,response,handler);
    }
}
