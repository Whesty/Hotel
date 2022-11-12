package com.example.hotel.forms;

import com.example.hotel.model.TypeRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomForm {
private Integer Id;
    private int Number;
    private TypeRoom typeRooms; // Тип номера
    private int idTypeRooms;
    private int CountPlaces; // Количество мест
    private float Price; // Цена
}
