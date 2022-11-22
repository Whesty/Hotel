package com.example.hotel.services;

import com.example.hotel.model.Room;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.Transient;
import java.sql.Date;
import java.util.List;

@Service
public class RoomServices {
    @Autowired
    private RoomRepository roomRepository;

    public @ResponseBody List<Room> getRooms(){
        return roomRepository.findAll();
    }
    //Сохранение номера
    @Transient
    public void saveRoom(Room room){
        roomRepository.save(room);
    }
    //Удаление номера
    public void deleteRoom(int id){
        roomRepository.deleteById(id);
    }
    //Поиск номера по id
    public Room findRoom(int id){
        return roomRepository.findById(id).get();
    }
    //Изменение номера по id
    public void updateRoom(int id, Room room){
        Room room1 = roomRepository.findById(id).get();
        room1.setNumber(room.getNumber());
        room1.setType_rooms(room.getType_rooms());
        room1.setCount_places(room.getCount_places());
        roomRepository.save(room1);
    }
    public void updateRoom(Room room){
        roomRepository.save(room);
    }

}

