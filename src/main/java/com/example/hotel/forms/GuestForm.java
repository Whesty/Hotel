package com.example.hotel.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestForm {
    private int id;
    private String lastname; //Фамилия
    private String firstname; //Имя
    private String secendname; //Отчество
    private String email; //Почта
    private Date birthday; //Дата рождения
    private String date_birthday;

}
