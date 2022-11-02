package com.example.hotel.model;

import java.util.Date;

// Заказы
public class Orders {
    int id; // Идентификатор
    Guest guest; // Гость сделавший заказ
    Service service; // номер
    Date dateOrders; // Дата заказа
    Date dateService; // Дата обслуживания
    Worker worker; // сотрудник
}
