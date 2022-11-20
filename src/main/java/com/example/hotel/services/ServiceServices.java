package com.example.hotel.services;

import com.example.hotel.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServices {
    @Autowired
    ServiceRepository serviceRepository;
    //Сохранение услуги
    public void saveService(com.example.hotel.model.Service service){
        serviceRepository.save(service);
    }
    //Удаление услуги
    public void deleteService(int id){
        serviceRepository.deleteById(id);
    }
    //Поиск услуги по id
    public com.example.hotel.model.Service findService(int id){
        return serviceRepository.findById(id).get();
    }
    //Список всех услуг
    public List<com.example.hotel.model.Service> getServices(){
        return serviceRepository.findAll();
    }
}
