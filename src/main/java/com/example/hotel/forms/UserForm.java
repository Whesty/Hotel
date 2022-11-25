package com.example.hotel.forms;

import com.example.hotel.model.ERole;
import com.example.hotel.model.Role;

import java.util.Collections;
import java.util.Set;

public class UserForm {
    private String login;
    private String password;
    private String role;

    public UserForm() {
    }

    public UserForm(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setRole(String role) {
        this.role = role;
    }

    public String getRoles() {
        return this.role;
    }
}
