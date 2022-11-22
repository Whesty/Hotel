package com.example.hotel.forms;

import com.example.hotel.model.Guest;
import com.example.hotel.model.Reservation;
import com.example.hotel.model.Room;
import com.example.hotel.services.GuestServices;
import com.example.hotel.services.RoomServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.font.FontRenderContext;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationForm {
    private int id;
    private int user_id;
    private int room_id;
    private int type_room_id;
    private int guest_id;
    private Guest guest;
    private Date date_in;
    private Date date_out;
    private int[] guests_id;
    public ReservationForm(Reservation reservation) {
        this.id = reservation.getId();
        this.user_id = reservation.getUser().getId();
        this.room_id = reservation.getRoom().getId();
        this.guest_id = reservation.getGuest().getId();
        this.date_in = reservation.getDate_in();
        this.date_out = reservation.getDate_out();
        this.type_room_id = reservation.getRoom().getType_rooms().getId();
        this.guests_id = reservation.getGuests().stream().mapToInt(guest -> guest.getId()).toArray();
    };
   /* public Reservation toReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(this.id);
        if(this.room_id != 0) {
            Room room = new Room();
            room.setId(this.room_id);
            room.SetToServices();
            reservation.setRoom(room);
        }else     {
            RoomServices roomServices = new RoomServices();
            Room room = roomServices.getFreeRoom(this.type_room_id, this.date_in, this.date_out);
        }
        Guest newGuest = new Guest();
        newGuest.setId(this.guest_id);
        newGuest.SetToServices();
        reservation.setGuest(newGuest);
        reservation.setDate_in(this.date_in);
        reservation.setDate_out(this.date_out);
        int i = this.guests_id.length;
        while (i-- > 0) {
            Guest guest = new Guest();
            guest.setId(this.guests_id[i]);
            guest.SetToServices();
            reservation.getGuests().add(newGuest);
        }
        return reservation;
    }*/
}
