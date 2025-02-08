package com.kursova.demo.config;

import com.kursova.demo.enums.UserRoleEnum;
import com.kursova.demo.repository.UserRepository;
import com.kursova.demo.service.DetailsUserServ;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import static org.thymeleaf.util.StringUtils.substring;

@Configuration
public class SecurityConfig {
    @Autowired
    private RequestCacheFilter requestCacheFilter;

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new DetailsUserServ(userRepository);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public RequestCache requestCache() {
        return new HttpSessionRequestCache(); // This saves the original request before redirect
    }
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Get the redirect URL from the session
            HttpSession session = request.getSession();
            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");

            //String fullUrl = request.getRequestURL().toString() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");

           // System.out.println("FULL URL : "+ fullUrl);
            // Log the redirectUrl for debugging
            System.out.println("Redirecting to: " + redirectUrl);
            String path = "";
            for(int i = 0 ; i < redirectUrl.length() ; i++){
                if(redirectUrl.charAt(i) == '='){
                    path = substring(redirectUrl,i+2,redirectUrl.length());
                    break;
                }
            }

            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                session.removeAttribute("redirectAfterLogin");  // Clean up the session attribute
                response.sendRedirect("http://localhost:8080/"+ path);  // Redirect the user to the original URL
            } else {
                response.sendRedirect("/");  // Default to home page if no URL was saved
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/static/**", "/css/**", "/img/**").permitAll()
                        .requestMatchers("/", "/user/login", "/user/register", "/user/failure", "/user/edit", "/maintenance").permitAll()
                        .requestMatchers(HttpMethod.GET, "/catalogue").permitAll()
                        .requestMatchers("/admin/**").hasRole(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/user/login")
                        .passwordParameter("password")
                        .usernameParameter("email")
                        .successHandler(customAuthenticationSuccessHandler())  // Use custom success handler
                        .failureForwardUrl("/user/failure"))
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            // Capture the full URL (including parameters) before redirecting
                            /* String redirectUrl = request.getRequestURI();
                        String queryString = request.getQueryString();
                        if (queryString != null) {
                            redirectUrl += "?" + queryString;
                        }
                        response.sendRedirect("/user/login?redirectAfterLogin=" + redirectUrl);
                    }))*/

                            String fullUrl = request.getRequestURL().toString() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
                            String queryString = request.getQueryString();
                            String redirectUrl = request.getRequestURI();
                            if (queryString != null) {
                                redirectUrl += "?" + queryString;
                            }
                           // System.out.println("Redirecting to login with full URL: " + fullUrl);
                            response.sendRedirect("/user/login?redirectAfterLogin=" + redirectUrl);  // Append full URL to login redirect
                        }))
                .build();
    }
}