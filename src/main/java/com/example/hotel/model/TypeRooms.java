package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeRooms {
    int Id;
    String Name;
    String Description;
    int Price;
    //Вывод информации о типе номера
    public String toString() {
        return Name;
    }
}
