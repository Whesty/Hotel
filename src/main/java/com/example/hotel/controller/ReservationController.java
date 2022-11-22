package com.example.hotel.controller;

import com.example.hotel.forms.ReservationForm;
import com.example.hotel.model.Reservation;
import com.example.hotel.model.TypeRoom;
import com.example.hotel.services.ReservationServices;
import com.example.hotel.services.TypeRoomServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping
public class ReservationController {
    public final ReservationServices reservationServices;
    public final TypeRoomServices typeRoomServices;

    public ReservationController(ReservationServices reservationServices, TypeRoomServices typeRoomServices) {
        this.reservationServices = reservationServices;
        this.typeRoomServices = typeRoomServices;
    }

    @GetMapping(value = {"/CreateReservation"})
    public ModelAndView CreateReservation(){
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
        Reservation reservation = reservationForm.toReservation();
        reservationServices.save(reservation);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
    return modelAndView;
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
        Reservation reservation = reservationForm.toReservation();
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
