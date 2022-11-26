package com.example.hotel.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsForm {
    int id;
    int worker;
    float prize;
    float salary;
    int work_hour;
    String date;
}
