package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String Name;
    String Surname;
    String Secendname; // Отчество
    String Position; // Должность
    String Phone; //
    String Email;
    int Experience; // Опыт работы
}
