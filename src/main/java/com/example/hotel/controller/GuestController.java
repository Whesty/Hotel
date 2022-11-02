package com.example.hotel.controller;
/*
 * Аннотация @RequestMapping используется для связывания с URL для всего класса
 * */

import com.example.hotel.forms.GuestForm;
import com.example.hotel.model.Guest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class GuestController {
    private static List<Guest> guests = new ArrayList<Guest>();
@GetMapping(value = {"/CreateGuest"})
    public ModelAndView SaveGuest(Model model){
    ModelAndView modelAndView = new ModelAndView("CreateGuest");
    GuestForm guestForm = new GuestForm();
    model.addAttribute("guestform", guestForm);
    log.info("/CreateGuest was called");
    return modelAndView;
}
@PostMapping(value = {"/CreateGuest"})
    public ModelAndView SaveGuest(Model model, @ModelAttribute("guestform") GuestForm guestForm){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    int id = guests.size();
    String Lastname = guestForm.getLastname();
    String Firstname = guestForm.getFirstname();
    String Secendname = guestForm.getSecendname();
    String email = guestForm.getEmail();
    Date birthday = guestForm.getBirthday();
    if (Lastname != null && Lastname.length() > 0 //
            && Firstname != null && Firstname.length() > 0 //
            && Secendname != null && Secendname.length() > 0 //
            && email != null && email.length() > 0) {
        Guest newGuest = new Guest(id, Lastname, Firstname, Secendname, email, birthday);
        guests.add(newGuest);
        model.addAttribute("guests", guests);
        log.info("Add Guest");
        return modelAndView;
    }
    model.addAttribute("errorMessage", "Error");
    modelAndView.setViewName("CreateGuest");
    log.info("/CreateGuest was called");
    return modelAndView;
}
}
