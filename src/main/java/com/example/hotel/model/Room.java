package com.example.hotel.model;

import com.example.hotel.services.ReservationServices;
import com.example.hotel.services.RoomServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

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
    public void SetToServices(){
        RoomServices roomServices = new RoomServices();
        Room newRoom = roomServices.findRoom(this.id);
        if (newRoom != null){
            this.number = newRoom.number;
            this.type_rooms = newRoom.type_rooms;
            this.count_places = newRoom.count_places;
        }
    }
    public float getPrice(){
        return count_places*10+ type_rooms.getPrice();
    }

    public Reservation getReservation() {
        ReservationServices reservationServices = new ReservationServices();
        return reservationServices.findReservationByRoom(this);
    }

    public Reservation getReservation(Date date_in, Date date_out) {
        ReservationServices reservationServices = new ReservationServices();
        return reservationServices.findReservationByRoom(this, date_in, date_out);
    }
}
