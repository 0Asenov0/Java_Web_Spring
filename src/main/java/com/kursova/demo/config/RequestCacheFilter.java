package com.kursova.demo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // Check if the request URL ends with common static resource extensions
        String uri = request.getRequestURI();
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".gif")) {
            // Skip processing for static resources
            filterChain.doFilter(request, response);
            return;
        }

        // If the user is unauthenticated and tries to access a protected page
        if (request.getRequestURI() != null && request.getUserPrincipal() == null) {
            // Save the full URL (including query parameters) the user is trying to access
            String fullUrl = request.getRequestURL().toString() +
                    (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            session.setAttribute("redirectAfterLogin", request.getQueryString() != null ?  request.getQueryString() : "");
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}

