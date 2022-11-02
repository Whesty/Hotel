package com.example.hotel.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestForm {
    private int Id;
    private String Lastname; //Фамилия
    private String Firstname; //Имя
    private String Secendname; //Отчество
    private String email; //Почта
    private Date Birthday; //Дата рождения

}
