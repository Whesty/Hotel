package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    Worker worker; // Идентификатор работника
    String login; // Логин
    String password;
    @OneToOne
    Role roles; // Роль

    public User(String login, String encode) {
        this.login = login;
        this.password = encode;
    }
}
