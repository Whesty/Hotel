package com.example.hotel.services;

import com.example.hotel.model.Guest;
import com.example.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServices {
    @Autowired
    GuestRepository guestRepository;
    //Сохранение гостя
    public void saveGuest(Guest guest){
        guestRepository.save(guest);
    }
    //Удаление гостя
    public void deleteGuest(int id){
        guestRepository.deleteById(id);
    }
    //Поиск гостя по id
    public Guest findGuest(int id){
        return guestRepository.findById(id).get();
    }
    //Изменение гостя по id
    public void updateGuest(Guest guest){
        guestRepository.save(guest);
    }
    //Список гостей
    public List<Guest> getGuests(){
        return guestRepository.findAll();
    }

}
