package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//Обьект Гость
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    private int Id;
    private String Lastname; //Фамилия
    private String Firstname; //Имя
    private String Secendname; //Отчество
    private String email; //Почта
    private Date Birthday; //Дата рождения


}
