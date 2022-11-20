package com.example.hotel.services;

import com.example.hotel.model.Orders;
import com.example.hotel.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServices {
    @Autowired
    OrdersRepository ordersRepository;
    //Сохранение заказа
    public void saveOrders(Orders orders){
        ordersRepository.save(orders);
    }
    //Удаление заказа
    public void deleteOrders(int id){
        ordersRepository.deleteById(id);
    }
    //Поиск заказа по id
    public Orders findOrders(int id){
        return ordersRepository.findById(id).get();
    }
    //Изменение заказа по id
    public void updateOrders(Orders orders){
        ordersRepository.save(orders);
    }
    //Список заказов
    public List<Orders> getOrders(){
        return ordersRepository.findAll();
    }

}
