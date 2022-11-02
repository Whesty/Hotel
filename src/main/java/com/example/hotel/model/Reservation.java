package com.example.hotel.model;

import java.util.Date;
import java.util.List;

// Бронь
public class Reservation {
    int id;
    User user; // Идентификатор пользователя который создал бронь
    Room room; // Идентификатор номера
    Guest guest; // Идентификатор гостя который забронировал номер
    Date dateIn; // Дата заезда
    Date dateOut; // Дата выезда
    List<Guest> guests; // Список гостей для которых забронировали номер
}
