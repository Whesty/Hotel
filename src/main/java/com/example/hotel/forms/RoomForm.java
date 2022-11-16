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
    private int number;
    private TypeRoom type_rooms; // Тип номера
    private int idTypeRooms;
    private int count_places; // Количество мест
    //private float Price; // Цена
  /*  private void SetPrice(){
        if (type_rooms != null) {
            Price = count_places*10+ type_rooms.getPrice();
        }
    }*/
   /* private RoomForm(int number, TypeRoom type_rooms, int count_places) {
        this.number = number;
        this.type_rooms = type_rooms;
        this.count_places = count_places;
        SetPrice();
    }*/
}
