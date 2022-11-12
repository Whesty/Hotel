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
    int Id;
    @OneToOne
    Worker Worker; // Идентификатор работника
    String Login; // Логин
    String Password;
    byte IsAdmin;
}
