package com.example.hotel.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceForm {
    private Integer id;
    private String service_name;
    private String info;
    private int price;
}
