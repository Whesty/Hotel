package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

// Выплата
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @OneToOne
    Worker worker; // Сотрудник
    float prize; // Премия
    float salary; // Зарплата
    float work_hour; // Рабочих часо
    Date date; // Дата выплаты
}
