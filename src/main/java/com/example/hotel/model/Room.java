package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Номер
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    int Number;
    //Наследование типов номеров из таблицы TypeRooms
    @OneToOne
    TypeRoom typeRooms; // Тип номера
    int CountPlaces; // Количество мест
    float Price; // Цена
    //Конйструктор Room
    public Room(int id, int number, TypeRoom typeRooms, int countPlaces) {
        Id = id;
        Number = number;
        this.typeRooms = typeRooms;
        CountPlaces = countPlaces;
        Price = typeRooms.price+CountPlaces*10;
    }
}
