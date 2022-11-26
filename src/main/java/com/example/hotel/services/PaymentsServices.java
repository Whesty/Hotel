package com.example.hotel.services;

import com.example.hotel.model.Payments;
import com.example.hotel.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsServices {
    @Autowired
    private PaymentsRepository paymentsRepository;

    // Получить все выплаты
    public List<Payments> getPayments(){
        return paymentsRepository.findAll();
    }
    // Получить выплату по id
    public Payments getPaymentsById(int id){
        return paymentsRepository.findById(id).orElse(null);
    }
    // Добавить выплату
    public Payments savePayments(Payments payments){
        return paymentsRepository.save(payments);
    }
    // Удалить выплату
    public void deletePayments(int id){
        paymentsRepository.deleteById(id);
    }
    // Изменить выплату

}
