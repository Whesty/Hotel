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
    Integer id;
    int number;
    //Наследование типов номеров из таблицы TypeRooms
    @OneToOne
    TypeRoom type_rooms; // Тип номера
    int count_places; // Количество мест

}
