package com.example.hotel.controller;
/*
 * Аннотация @RequestMapping используется для связывания с URL для всего класса
 * */

import com.example.hotel.forms.GuestForm;
import com.example.hotel.model.Guest;
import com.example.hotel.services.GuestServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.sql.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class GuestController {
    // Список гостей

    private final GuestServices guestServices;

    public GuestController(GuestServices guestServices) {
        this.guestServices = guestServices;
    }
    /*  private static List<Guest> guests = new ArrayList<Guest>();*/

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
    if (guestForm != null) {
        try {
            log.info(createGuest(guestForm));
            List<Guest> guests = guestServices.getGuests();
            log.info("Guests: " + guests);
            return modelAndView;
        } catch (Exception err){
            model.addAttribute("errorMessage", err.getMessage());
        }
    }
    model.addAttribute("errorMessage", "Error");
    modelAndView.setViewName("CreateGuest");
    log.info("/CreateGuest was called");
    return modelAndView;
}

//Функция для создания гостя
    public String createGuest(GuestForm guestForm){
        List<Guest> guests = guestServices.getGuests();
        int id = guests.size();
        String Lastname = guestForm.getLastname();
        String Firstname = guestForm.getFirstname();
        String Secendname = guestForm.getSecendname();
        String email = guestForm.getEmail();
        Date birthday = guestForm.getBirthday();
        Guest newGuest = new Guest(id+1, Lastname, Firstname, Secendname, email, birthday);
        guestForm.setId(id+1);
        if(guests.equals(newGuest)){
            log.info("Guest already exists");
            return "Guest already exists";
        }
        else {
            guestServices.saveGuest(newGuest);
            log.info("Guest added");
            return "Guest added";
        }
    }
// Удаление гостя
    @GetMapping(value = {"/DeleteGuest/{id}"})
        public ModelAndView DeleteGuest(Model model, @PathVariable("id") int id){
    try{
        ModelAndView modelAndView = new ModelAndView("DeleteGuest");
        GuestForm guestForm = new GuestForm();
        guestForm.setId(id);
        model.addAttribute("guestform", guestForm);
        log.info("/DeleteGuest was called");
        return  modelAndView;
    } catch (Exception err){
        model.addAttribute("errorMessage", err.getMessage());
    }
        return new ModelAndView("redirect:/ViewGuest");
    }
    @PostMapping(value = {"/DeleteGuest"})
        public ModelAndView DeleteGuest(Model model, @ModelAttribute("guestform") GuestForm guestForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<Guest> guests = guestServices.getGuests();
        int id = guestForm.getId();
        if (id > 0) {
            try {
                guestServices.deleteGuest(id);
                model.addAttribute("guests", guests);
                log.info("Delete Guest");
                return modelAndView;
            } catch (Exception err){
                model.addAttribute("errorMessage", err.getMessage());
            }
        }
        model.addAttribute("errorMessage", "Error");
        modelAndView.setViewName("DeleteGuest");
        log.info("/DeleteGuest was called");
        return modelAndView;
    }
    //Изменение гостя
    @GetMapping(value = {"/EditGuest/{id}"})
        public ModelAndView UpdateGuest(Model model, @PathVariable("id") int id) {
    try{
        ModelAndView modelAndView = new ModelAndView("EditGuest");
        GuestForm guestForm = new GuestForm();
        guestForm.setId(id);
        Guest guest = guestServices.findGuest(id);
        guestForm.setLastname(guest.getLastname());
        guestForm.setFirstname(guest.getFirstname());
        guestForm.setSecendname(guest.getSecendname());
        guestForm.setEmail(guest.getEmail());
        guestForm.setBirthday(guest.getBirthday());
        model.addAttribute("guestform", guestForm);
        log.info("/UpdateGuest was called");
        return modelAndView;
    } catch (Exception err){
        model.addAttribute("errorMessage", err.getMessage());
        return new ModelAndView("redirect:/ViewGuest");
    }
    }
    @PostMapping(value = {"/EditGuest"})
        public ModelAndView UpdateGuest(Model model, @ModelAttribute("guestform") GuestForm guestForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<Guest> guests = guestServices.getGuests();
        int id = guestForm.getId();
        String Lastname = guestForm.getLastname();
        String Firstname = guestForm.getFirstname();
        String Secendname = guestForm.getSecendname();
        String email = guestForm.getEmail();
        Date birthday = guestForm.getBirthday();
        if (id > 0 && Lastname != null && Lastname.length() > 0 //
                && Firstname != null && Firstname.length() > 0 //
                && Secendname != null && Secendname.length() > 0 //
                && email != null && email.length() > 0) {
            try{
            Guest newGuest = new Guest(id, Lastname, Firstname, Secendname, email, birthday);
            guestServices.updateGuest(newGuest);
            model.addAttribute("guests", guests);
            log.info("Update Guest");
            return modelAndView;
            } catch (Exception err){
                model.addAttribute("errorMessage", err.getMessage());
                modelAndView.setViewName("EditGuest");
            }
        }
        model.addAttribute("errorMessage", "Error");
        modelAndView.setViewName("EditGuest");
        log.info("/UpdateGuest was called");
        return modelAndView;
    }
    //Список гостей
    @GetMapping(value = {"/ViewGuest"})
        public ModelAndView GuestList(Model model) {
        ModelAndView modelAndView = new ModelAndView("ViewGuest");
        List<Guest> guests = guestServices.getGuests();
        model.addAttribute("guests", guests);
        log.info("/ViewGuest was called");
        return modelAndView;
    }
    //Создать гостя в Reservation
    @GetMapping(value = {"/CreateGuestReservation"})
        public ModelAndView SaveGuestReservation(Model model){
        ModelAndView modelAndView = new ModelAndView("CreateGuestReservation");
        GuestForm guestForm = new GuestForm();
        model.addAttribute("guestform", guestForm);
        log.info("/CreateGuestReservation was called");
        return modelAndView;
    }
    @PostMapping(value = {"/CreateGuestReservation"})
        public ModelAndView SaveGuestReservation(Model model, @ModelAttribute("guestform") GuestForm guestForm){
        ModelAndView modelAndView = new ModelAndView();
        if (guestForm != null) {
            log.info(createGuest(guestForm));
            modelAndView.addObject("guest_id", guestForm.getId());
            log.info("Guest: " + guestForm.getId() );
            return modelAndView;
        }
        model.addAttribute("errorMessage", "Error");
        modelAndView.setViewName("CreateReservation");
        log.info("/CreateGuestReservation was called");
        return modelAndView;
    }

}
