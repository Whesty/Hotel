package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

// Заказы
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; // Идентификатор
    @OneToOne
    Guest guest; // Гость сделавший заказ
    @OneToOne
    Service service; // номер
    Date dateOrders; // Дата заказа
    Date dateService; // Дата обслуживания
    @OneToOne
    Worker worker; // сотрудник
}
