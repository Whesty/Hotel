package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Номер
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    int Id;
    int Number;
    TypeRooms typeRooms; // Тип номера
    int CountPlaces; // Количество мест
    float Price; // Цена
    //Конйструктор Room
    public Room(int id, int number, TypeRooms typeRooms, int countPlaces) {
        Id = id;
        Number = number;
        this.typeRooms = typeRooms;
        CountPlaces = countPlaces;
        Price = typeRooms.Price+CountPlaces*10;
    }
}
