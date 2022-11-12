package com.example.hotel.services;

import com.example.hotel.model.TypeRoom;
import com.example.hotel.repository.TypeRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class TypeRoomServices {
    @Autowired
    TypeRoomRepository typeRoomsRepository;
    public @ResponseBody List<TypeRoom> getTypeRooms(){
        return typeRoomsRepository.findAll();
    }
    //Сохранение типа номера
    public void saveTypeRooms(TypeRoom typeRooms){
        typeRoomsRepository.save(typeRooms);
    }
    //Удаление типа номера
    public void deleteTypeRooms(int id){
        typeRoomsRepository.deleteById(id);
    }
    //Поиск типа номера по id
    public @ResponseBody TypeRoom findTypeRooms(int id){
        return typeRoomsRepository.findById(id).get();
    }
    //Изменение типа номера
    public void updateTypeRooms(TypeRoom typeRooms){
        typeRoomsRepository.save(typeRooms);
    }

}
