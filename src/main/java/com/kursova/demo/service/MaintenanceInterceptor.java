package com.kursova.demo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;

@Configuration
public class MaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();

        // Allow static resources
        if (requestUrl.startsWith("/css/") || requestUrl.startsWith("/js/") || requestUrl.startsWith("/images/")) {
            return true; // Let Spring continue processing the request
        }

        if (!requestUrl.equals("/maintenance")) {
            LocalTime timeNow = LocalTime.now();

            if (timeNow.getHour() == 17 && timeNow.getMinute() == 35) {
                response.sendRedirect("/maintenance");
                return false; // Stop processing and redirect to /maintenance
            }

        }
        else if(requestUrl.equals("/maintenance")){
            LocalTime timeNow = LocalTime.now();
            if (timeNow.getHour() == 17 && timeNow.getMinute() == 36) {
                response.sendRedirect("/");
                return false; // Stop processing and redirect to home
            }
        }

        return true; // Continue processing if no maintenance conditions met
    }

}
