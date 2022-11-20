package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

// Бронь
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne
    User user; // Идентификатор пользователя который создал бронь
    @OneToOne
    Room room; // Идентификатор номера
    @OneToOne
    Guest guest; // Идентификатор гостя который забронировал номер
    Date date_in; // Дата заезда
    Date date_out; // Дата выезда
    @ManyToMany
    List<Guest> guests; // Список гостей для которых забронировали номер
}
