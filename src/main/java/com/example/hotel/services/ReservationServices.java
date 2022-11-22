package com.example.hotel.services;

import com.example.hotel.forms.ReservationForm;
import com.example.hotel.model.Reservation;
import com.example.hotel.model.Room;
import com.example.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReservationServices {
    @Autowired
    public ReservationRepository reservationRepository;

    //Сохранение бронирования
    public void save(Reservation reservation){
        reservationRepository.save(reservation);
    }
    //Удаление бронирования
    public void delete(Reservation reservation){
        reservationRepository.delete(reservation);
    }
    //Поиск бронирования по id
    public Reservation findById(int id){
        return reservationRepository.findById(id).get();
    }
    //Список броней
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public Reservation findReservationByRoom(Room room) {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation: reservations) {
            if (reservation.getRoom().getId() == room.getId()){
                return reservation;
            }
        }
        return null;
    }
    //Получить бронь в районе дат
    public  Reservation findReservationByRoom(Room room, Date date_in, Date date_out) {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation: reservations) {
            //Если дата заезда больше даты брони и дата выезда меньше даты брони
            if (reservation.getRoom().getId() == room.getId() && date_in.after(reservation.getDate_in()) && date_out.before(reservation.getDate_out())){
                return reservation;
            }
        }
        return null;
    }
    //Получить бронь в районе дат
    public Reservation findReservationByRoom(Room room, Date date_in, Date date_out, int id) {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation: reservations) {
            //Если дата заезда больше даты брони и дата выезда меньше даты брони
            if (reservation.getRoom().getId() == room.getId() && date_in.after(reservation.getDate_in()) && date_out.before(reservation.getDate_out()) && reservation.getId() != id){
                return reservation;
            }
        }
        return null;
    }
}
