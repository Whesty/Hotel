package com.example.hotel.forms;

import com.example.hotel.model.Guest;
import com.example.hotel.model.Service;
import com.example.hotel.model.Worker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersForm {

    int id; // Идентификатор
    Guest guest; // Гость сделавший заказ
    Service service; // номер
    Date date_orders; // Дата заказа
    Date date_service; // Дата обслуживания
    Worker worker; // сотрудник
}
