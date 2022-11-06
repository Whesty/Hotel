package com.example.hotel.model;

import javax.persistence.*;

import java.util.Date;

// Выплата
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @OneToOne
    Worker worker; // Сотрудник
    float prize; // Премия
    float salary; // Зарплата
    float workhour; // Рабочих часо
    int month; // Месяц
    int year; // Год
}
