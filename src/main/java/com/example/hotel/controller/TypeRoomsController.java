package com.example.hotel.controller;

import com.example.hotel.forms.TypeRoomForm;
import com.example.hotel.model.TypeRoom;
import com.example.hotel.services.TypeRoomServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            TypeRoom newTypeRoom = new TypeRoom(null, name, description,price);
            typeRoomsServices.saveTypeRooms(newTypeRoom);
        }
        modelAndView.setViewName("ViewTypeRooms");

        modelAndView.addObject("typerooms", typeRoomsServices.getTypeRooms());
        return modelAndView;
    }

    //Изменение типа комнаты
    @GetMapping("/EditTypeRoom/{id}")
    public ModelAndView editTypeRoom(Model model, @PathVariable String id){
            ModelAndView modelAndView = new ModelAndView("EditTypeRoom");
            TypeRoomForm typeRoomForm = new TypeRoomForm();
            TypeRoom typeRoom = typeRoomsServices.findTypeRooms(Integer.parseInt(id));
            typeRoomForm.setId(typeRoom.getId());
            typeRoomForm.setName_type(typeRoom.getName_type());
            typeRoomForm.setInfo(typeRoom.getInfo());
            typeRoomForm.setPrice(typeRoom.getPrice());
            model.addAttribute("typeroomform", typeRoomForm);
            return modelAndView;
    }
    @PostMapping("/EditTypeRoom")
    public ModelAndView editTypeRoom(Model model, @ModelAttribute("typeroomform") TypeRoomForm typeRoomForm){
        ModelAndView modelAndView = new ModelAndView();
        Integer idnew = typeRoomForm.getId();
        String name = typeRoomForm.getName_type();
        String description = typeRoomForm.getInfo();
        float price = typeRoomForm.getPrice();
        if (idnew!=null && name != null  && description != null && price != 0) {
            TypeRoom newTypeRoom = new TypeRoom(idnew, name, description, price);
            typeRoomsServices.updateTypeRooms(idnew, newTypeRoom);
        }
        modelAndView.setViewName("ViewTypeRooms");
        modelAndView.addObject("typerooms", typeRoomsServices.getTypeRooms());
        return modelAndView;
    }
    //Удаление типа комнаты
    @GetMapping("/DeleteTypeRoom/{id}")
    public ModelAndView deleteTypeRoom(Model model, @PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("DeleteTypeRoom");
        TypeRoomForm typeRoomForm = new TypeRoomForm();
        TypeRoom typeRoom = typeRoomsServices.findTypeRooms(Integer.parseInt(id));
        typeRoomForm.setId(typeRoom.getId());
        model.addAttribute("typeroomform", typeRoomForm);
        return modelAndView;
    }
    @PostMapping("/DeleteTypeRoom")
    public ModelAndView deleteTypeRoom(Model model, @ModelAttribute("typeroomform") TypeRoomForm typeRoomForm){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = typeRoomForm.getId();
        if (id!=null) {
            typeRoomsServices.deleteTypeRooms(id);
        }
        modelAndView.setViewName("ViewTypeRooms");
        modelAndView.addObject("typerooms", typeRoomsServices.getTypeRooms());
        return modelAndView;
    }
}
