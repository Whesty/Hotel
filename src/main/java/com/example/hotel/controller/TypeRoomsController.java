package com.example.hotel.controller;

import com.example.hotel.forms.TypeRoomForm;
import com.example.hotel.model.TypeRoom;
import com.example.hotel.services.TypeRoomServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@ComponentScan("com.example.hotel.repository")
public class TypeRoomsController {

    public final TypeRoomServices typeRoomsServices;
    public TypeRoomsController(TypeRoomServices typeRoomsServices) {
        this.typeRoomsServices = typeRoomsServices;
    }
    @GetMapping("/ViewTypeRooms")
    public ModelAndView typeRooms(Model model){
        ModelAndView modelAndView = new ModelAndView("ViewTypeRooms");
        List<TypeRoom> typerooms = typeRoomsServices.getTypeRooms();
        model.addAttribute("typerooms", typerooms);
        return modelAndView;
    }

    @GetMapping("/CreateTypeRoom")
    public ModelAndView createTypeRoom(Model model){
        ModelAndView modelAndView = new ModelAndView("CreateTypeRoom");
        TypeRoomForm typeRoomForm = new TypeRoomForm();
        model.addAttribute("typeroomform", typeRoomForm);
        return modelAndView;
    }
    @PostMapping("/CreateTypeRoom")
    public ModelAndView createTypeRoom(Model model, @ModelAttribute("typeroomform") TypeRoomForm typeRoomForm){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = typeRoomsServices.getTypeRooms().size();
        String name = typeRoomForm.getName_type();
        String description = typeRoomForm.getInfo();
        float price = typeRoomForm.getPrice();
        if (name != null  && description != null && price != 0) {
            TypeRoom newTypeRoom = new TypeRoom(id, name, description,price);
            typeRoomsServices.saveTypeRooms(newTypeRoom);
        }
        modelAndView.setViewName("ViewTypeRooms");
        modelAndView.addObject("typeRooms", typeRoomsServices.getTypeRooms());
        return modelAndView;
    }
}
