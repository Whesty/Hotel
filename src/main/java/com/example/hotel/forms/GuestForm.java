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
    private String lastname; //Фамилия
    private String firstname; //Имя
    private String secendname; //Отчество
    private String email; //Почта
    private Date birthday; //Дата рождения

}
