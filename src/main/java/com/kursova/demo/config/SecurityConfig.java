package com.kursova.demo.config;

import com.kursova.demo.enums.UserRoleEnum;
import com.kursova.demo.repository.UserRepository;
import com.kursova.demo.service.DetailsUserServ;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new DetailsUserServ(userRepository);

    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.authorizeHttpRequests(
                //Define which urls are visible by users
                authorizeRequest ->   authorizeRequest
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/static/**", "/css/**", "/img/**").permitAll()
                        .requestMatchers("/","/user/login","/user/register","/user/failure","/user/edit","/maintenance").permitAll()
                        .requestMatchers(HttpMethod.GET,"/catalogue").permitAll()
                        .requestMatchers("/admin/**").hasRole(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin->{
            formLogin
                    .loginPage("/user/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .failureForwardUrl("/user/failure");

        })
                .logout(
                        logout -> {
                            logout
                                   .logoutUrl("/user/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true)
                                    .deleteCookies("JSESSIONID");
                        }
                ).build();


        }


}
