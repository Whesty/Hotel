package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TypeRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String Name;
    String Description;
    int Price;
    //Вывод информации о типе номера
    public String toString() {
        return Name;
    }
}
