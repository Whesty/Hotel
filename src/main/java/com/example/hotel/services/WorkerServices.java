package com.example.hotel.services;

import com.example.hotel.model.Worker;
import com.example.hotel.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServices {
    @Autowired
    WorkerRepository workerRepository;

    //Список всех работников
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }
    //Поиск работников по id
    public Worker findById(int id) {
        return workerRepository.findById(id).orElse(null);
    }
    //Список работников по имени
    /*public List<Worker> findByName(String name) {
        return workerRepository.findByName(name);
    }
    //Список работников по должности
    public List<Worker> findByPosition(String position) {
        return workerRepository.findByPosition(position);
    }*/
    //Сохранение работника
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }
    //Удаление работника
    public void delete(int id) {
        workerRepository.deleteById(id);
    }
}
