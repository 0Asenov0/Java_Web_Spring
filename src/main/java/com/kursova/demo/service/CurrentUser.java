package com.kursova.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component("currentUser")
@SessionScope
public class CurrentUser {
    private boolean logged;
    private String firstName;
    private  String username;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean Logged() {
        return logged;
    }

    public void setLogged(boolean active) {
        logged = active;
    }
    public void logOut(){
        setLogged(false);
        setFirstName(null);
        setUsername(null);
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "name='" + firstName + '\'' +
                ", lastName='" + username + '\'' +
                ", isLogged=" + logged +
                '}';
    }
}
