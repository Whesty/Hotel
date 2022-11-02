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
    private int id;
    private String lastname; //Фамилия
    private String firstname; //Имя
    private String secendname; //Отчество
    private String email; //Почта
    private Date birthday; //Дата рождения


}
