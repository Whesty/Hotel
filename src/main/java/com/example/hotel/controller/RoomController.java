package com.example.hotel.controller;

import com.example.hotel.forms.RoomForm;
import com.example.hotel.model.Room;
import com.example.hotel.model.TypeRoom;
import com.example.hotel.services.RoomServices;
import com.example.hotel.services.TypeRoomServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@ComponentScan("com.example.hotel.repository")
public class RoomController {
    // Список номеров

    private final RoomServices roomServices;


    public RoomController(RoomServices roomServices, TypeRoomServices typeRoomsServices) {
        this.roomServices = roomServices;
        this.typeRoomsServices = typeRoomsServices;
    }
private final TypeRoomServices typeRoomsServices;

    //Вызов формы создания номера
    @GetMapping(value = {"/CreateRoom"})
    public ModelAndView SaveRoom(Model model){
        ModelAndView modelAndView = new ModelAndView("CreateRoom");
        RoomForm roomForm = new RoomForm();
        List<TypeRoom> typerooms = typeRoomsServices.getTypeRooms();
        modelAndView.addObject("typeroomList", typerooms);
        model.addAttribute("roomform", roomForm);
        log.info("/CreateRoom was called");
        return modelAndView;
    }
    //Сохранение номера
    @PostMapping(value = {"/CreateRoom"})
    public ModelAndView SaveRoom(Model model, @ModelAttribute("roomform") RoomForm roomForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        int id = roomServices.getRooms().size();
        //int id = rooms.size();
        int number = roomForm.getNumber();
        int idtype = roomForm.getIdTypeRooms();
        List<TypeRoom> typerooms = typeRoomsServices.getTypeRooms();
        TypeRoom typeRooms = typerooms.get(idtype-1);
        int countPlaces = roomForm.getCountPlaces();
        if (number != 0 && typeRooms != null && countPlaces != 0) {
            Room newRoom = new Room(id, number, typeRooms, countPlaces);
            roomServices.saveRoom(newRoom);
            List<Room> rooms = roomServices.getRooms();
            model.addAttribute("rooms", rooms);
            log.info("Add Room");
            return modelAndView;
        }

        log.info("/Index was called");
        return modelAndView;
    }
    //Вызов формы редактирования номера
    @GetMapping(value = {"/EditRoom/{id}"})
    public ModelAndView EditRoom(Model model, @PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("EditRoom");
        RoomForm roomForm = new RoomForm();
        roomForm.setId(Integer.parseInt(id));
        List<TypeRoom> typerooms = typeRoomsServices.getTypeRooms();
        modelAndView.addObject("typeroomList", typerooms);
        roomForm.setNumber(roomServices.findRoom(Integer.parseInt(id)).getNumber());
        roomForm.setCountPlaces(roomServices.findRoom(Integer.parseInt(id)).getCountPlaces());
        model.addAttribute("roomform", roomForm);
        model.addAttribute("id", id);
        log.info("/EditRoom was called");
        return modelAndView;
    }
    //Редактирование номера
    @PostMapping(value = {"/EditRoom/{id}"})
    public ModelAndView EditRoom(Model model, @ModelAttribute("roomform") RoomForm roomForm, @ModelAttribute("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        int idRoom = Integer.parseInt(id);
        int number = roomForm.getNumber();
        TypeRoom typeRooms = roomForm.getTypeRooms();
        int countPlaces = roomForm.getCountPlaces();
        if (number != 0 && typeRooms != null && countPlaces != 0) {
            Room newRoom = new Room(idRoom, number, typeRooms, countPlaces);
            //rooms.set(idRoom, newRoom);
            //model.addAttribute("rooms", rooms);
            roomServices.updateRoom(newRoom);
            log.info("Edit Room");
            return modelAndView;
        }

        log.info("/Index was called");
        return modelAndView;
    }
    //Удаление номера
    @GetMapping(value = {"/DeleteRoom"})
    public ModelAndView DeleteRoom(Model model){
        ModelAndView modelAndView = new ModelAndView("DeleteRoom");
        RoomForm roomForm = new RoomForm();
        model.addAttribute("roomform", roomForm);
        log.info("/DeleteRoom was called");
        return modelAndView;
    }
    //Удаление номера
    @PostMapping(value = {"/DeleteRoom"})
    public ModelAndView DeleteRoom(Model model, @ModelAttribute("roomform") RoomForm roomForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        int id = roomForm.getId();
        roomServices.deleteRoom(id);
       // model.addAttribute("rooms", rooms);
        log.info("Delete Room");
        return modelAndView;
    }
    //Просмотр всех номеров
    @GetMapping(value = {"/ViewRooms"})
    public ModelAndView ViewRooms(Model model){
        ModelAndView modelAndView = new ModelAndView("ViewRooms");
        List<Room> rooms = roomServices.getRooms();
        model.addAttribute("rooms", rooms);
        log.info("/ViewRooms was called");
        return modelAndView;
    }
}
