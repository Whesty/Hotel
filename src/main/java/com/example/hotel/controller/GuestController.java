package com.example.hotel.controller;
/*
 * Аннотация @RequestMapping используется для связывания с URL для всего класса
 * */

import com.example.hotel.model.Guest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class GuestController {
    private static List<Guest> guests = new ArrayList<Guest>();

}
