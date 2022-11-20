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
    /*private final GuestRepository guestRepository;
    @Autowired
    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }*/
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
    List<Guest> guests = guestServices.getGuests();
    int id = guests.size();
    String Lastname = guestForm.getLastname();
    String Firstname = guestForm.getFirstname();
    String Secendname = guestForm.getSecendname();
    String email = guestForm.getEmail();
    Date birthday = guestForm.getBirthday();
    if (Lastname != null && Lastname.length() > 0 //
            && Firstname != null && Firstname.length() > 0 //
            && Secendname != null && Secendname.length() > 0 //
            && email != null && email.length() > 0 //
            && birthday != null) {
        Guest newGuest = new Guest(id+1, Lastname, Firstname, Secendname, email, birthday);
        guests.add(newGuest);
        model.addAttribute("guests", guests);
        guestServices.saveGuest(newGuest);
        log.info("Add Guest");
        log.info("Guests: " + guests);
        return modelAndView;
    }
    model.addAttribute("errorMessage", "Error");
    modelAndView.setViewName("CreateGuest");
    log.info("/CreateGuest was called");
    return modelAndView;
}
// Удаление гостя
    @GetMapping(value = {"/DeleteGuest/{id}"})
        public ModelAndView DeleteGuest(Model model, @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("DeleteGuest");
        GuestForm guestForm = new GuestForm();
        guestForm.setId(id);
        model.addAttribute("guestform", guestForm);
        log.info("/DeleteGuest was called");
        return modelAndView;
    }
    @PostMapping(value = {"/DeleteGuest"})
        public ModelAndView DeleteGuest(Model model, @ModelAttribute("guestform") GuestForm guestForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<Guest> guests = guestServices.getGuests();
        int id = guestForm.getId();
        if (id > 0) {
            guestServices.deleteGuest(id);
            model.addAttribute("guests", guests);
            log.info("Delete Guest");
            return modelAndView;
        }
        model.addAttribute("errorMessage", "Error");
        modelAndView.setViewName("DeleteGuest");
        log.info("/DeleteGuest was called");
        return modelAndView;
    }
    //Изменение гостя
    @GetMapping(value = {"/EditGuest/{id}"})
        public ModelAndView UpdateGuest(Model model, @PathVariable("id") int id) {
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
            Guest newGuest = new Guest(id, Lastname, Firstname, Secendname, email, birthday);
            guestServices.updateGuest(newGuest);
            model.addAttribute("guests", guests);
            log.info("Update Guest");
            return modelAndView;
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
}
