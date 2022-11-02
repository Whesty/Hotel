package com.example.hotel.model;

import java.util.Date;

// Заказы
public class Orders {
    int Id; // Идентификатор
    Guest guest; // Гость сделавший заказ
    Service service; // номер
    Date DateOrders; // Дата заказа
    Date DateService; // Дата обслуживания
    Worker worker; // сотрудник
}
