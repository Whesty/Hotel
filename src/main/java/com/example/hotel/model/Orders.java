package com.example.hotel.model;

import java.util.Date;

// Заказы
public class Orders {
    int Id; // Идентификатор
    int IdGuest; // Идентификатор пользователя
    int IdService; // Идентификатор номера
    Date DateOrders; // Дата заказа
    Date DateService; // Дата обслуживания
    int IdWorker; // Идентификатор сотрудника
}
