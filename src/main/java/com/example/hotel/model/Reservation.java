package com.example.hotel.model;

import java.util.Date;
import java.util.List;

// Бронь
public class Reservation {
    int id;
    int idUser; // Идентификатор пользователя который создал бронь
    int idRoom; // Идентификатор номера
    int idGuest; // Идентификатор гостя который забронировал номер
    Date dateIn; // Дата заезда
    Date dateOut; // Дата выезда
    List<Guest> guests; // Список гостей для которых забронировали номер
}
