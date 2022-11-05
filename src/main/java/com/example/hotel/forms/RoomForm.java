package com.example.hotel.forms;

import com.example.hotel.model.TypeRooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomForm {
private int Id;
    private int Number;
    private TypeRooms typeRooms; // Тип номера
    private int CountPlaces; // Количество мест
    private float Price; // Цена
}
