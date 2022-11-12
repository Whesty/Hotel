package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="typerooms")
public class TypeRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name_type;
    String info;
    float price;

    public TypeRoom(Integer Id, String Name, String info) {
        id = Id;
        name_type = Name;
        this.info = info;
    }
}
