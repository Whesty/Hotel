package com.example.hotel.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeRoomForm {
        private Integer id;
        private String name_type;
        private String info;
        private float price;
        //Вывод информации о типе номера
        public String toString() {
            return name_type;
        }

}
