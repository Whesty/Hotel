package com.example.hotel.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;



//Журнал событий
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEvents {
    @Id
    private Integer id;
    //Событие
    private String event;
    //Дата события
    private Date date_create;
    //Пользователь
    @ManyToOne
    private User user;

}
