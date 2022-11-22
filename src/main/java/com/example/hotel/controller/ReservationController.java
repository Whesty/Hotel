package com.example.hotel.controller;

import com.example.hotel.forms.ReservationForm;
import com.example.hotel.model.Guest;
import com.example.hotel.model.Reservation;
import com.example.hotel.model.Room;
import com.example.hotel.model.TypeRoom;
import com.example.hotel.services.GuestServices;
import com.example.hotel.services.ReservationServices;
import com.example.hotel.services.RoomServices;
import com.example.hotel.services.TypeRoomServices;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping
public class ReservationController {
    public final ReservationServices reservationServices;
    public final TypeRoomServices typeRoomServices;
    public final RoomServices roomServices;
    public final GuestServices guestServices;

    public ReservationController(ReservationServices reservationServices, TypeRoomServices typeRoomServices, RoomServices roomServices, GuestServices guestServices) {
        this.reservationServices = reservationServices;
        this.typeRoomServices = typeRoomServices;
        this.roomServices = roomServices;
        this.guestServices = guestServices;
    }

    @GetMapping(value = {"/CreateReservation/{id}"})
    public ModelAndView CreateReservation(Mode model, @PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("CreateReservation");
        ReservationForm reservationForm = new ReservationForm();
        reservationForm.setGuest_id(id);
        reservationForm.setGuest(guestServices.findGuest(id));
        modelAndView.addObject("reservationform", reservationForm);
        List<TypeRoom> typeRooms = typeRoomServices.getTypeRooms();
        modelAndView.addObject("typeroomList", typeRooms);
        log.info("/CreateReservation was called");
        return modelAndView;
    }
    //Первая свободная комната по типу
    public Room getFreeRoom(int type, Date date_in, Date date_out){
        List<Room> rooms = roomServices.getRooms();
        for (Room room: rooms) {
            if (room.getType_rooms().getId() == type && reservationServices.findReservationByRoom(room, date_in, date_out) == null){
                return room;
            }
        }
        return null;
    }
    @GetMapping(value = {"/CreateReservation"})
    public ModelAndView CreateReservation(Mode model){
        ModelAndView modelAndView = new ModelAndView("CreateReservation");
        ReservationForm reservationForm = new ReservationForm();
        modelAndView.addObject("reservationform", reservationForm);
        List<TypeRoom> typeRooms = typeRoomServices.getTypeRooms();
        modelAndView.addObject("typeroomList", typeRooms);
        log.info("/CreateReservation was called");
        return modelAndView;
    }
    @PostMapping(value = {"/CreateReservation"})
    public ModelAndView CreateReservation(ReservationForm reservationForm){
        Reservation reservation = toReservation(reservationForm);
        reservationServices.save(reservation);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
    return modelAndView;
    }
    public Reservation toReservation(ReservationForm reservationForm) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationForm.getId());
        if(reservationForm.getRoom_id() != 0) {
            Room room = roomServices.findRoom(reservationForm.getRoom_id());
            reservation.setRoom(room);
        }else     {
            RoomServices roomServices = new RoomServices();
            Room room = getFreeRoom(reservationForm.getType_room_id(), reservationForm.getDate_in(), reservationForm.getDate_out());
        }
        Guest newGuest = guestServices.findGuest(reservationForm.getGuest_id());
        reservation.setGuest(newGuest);
        reservation.setDate_in(reservationForm.getDate_in());
        reservation.setDate_out(reservationForm.getDate_out());
        return reservation;
    }
    //Удаление бронирования
    @GetMapping(value = {"/DeleteReservation/{id}"})
    public ModelAndView DeleteReservation(Model model, @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("ViewReservation");
        reservationServices.delete(reservationServices.findById(id));
        List<Reservation> reservations = reservationServices.findAll();
        modelAndView.addObject("reservations", reservations);
        log.info("/DeleteReservation was called");
        return modelAndView;
    }
    //Изменение бронирования
    @GetMapping(value = {"/UpdateReservation/{id}"})
    public ModelAndView UpdateReservation(Model model, @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("UpdateReservation");
        Reservation reservation = reservationServices.findById(id);
        ReservationForm reservationForm = new ReservationForm(reservation);
        model.addAttribute("reservationform", reservationForm);
        List<TypeRoom> typeRooms = typeRoomServices.getTypeRooms();
        modelAndView.addObject("typeroomList", typeRooms);
        log.info("/UpdateReservation was called");
        return modelAndView;
    }
    @PostMapping(value = {"/UpdateReservation"})
    public ModelAndView UpdateReservation(ReservationForm reservationForm, @PathVariable("id") int id){
        Reservation reservation = toReservation(reservationForm);
        reservationServices.save(reservation);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ViewReservation");
        modelAndView.addObject("reservations", reservationServices.findAll());
        return modelAndView;
    }
    //Просмотр бронирований
    @GetMapping(value = {"/ViewReservation"})
    public ModelAndView ViewReservation(Model model){
        ModelAndView modelAndView = new ModelAndView("ViewReservation");
        List<Reservation> reservations = reservationServices.findAll();
        modelAndView.addObject("reservations", reservations);
        log.info("/ViewReservation was called");
        return modelAndView;
    }
}
