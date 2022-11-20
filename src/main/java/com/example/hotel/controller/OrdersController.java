package com.example.hotel.controller;

import com.example.hotel.forms.OrdersForm;
import com.example.hotel.services.OrdersServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping
public class OrdersController {
    OrdersServices ordersServices;

    @GetMapping(value = {"/CreateOrders"})
    public ModelAndView CreateOrders(Model model){
        ModelAndView modelAndView = new ModelAndView("CreateOrders");
        OrdersForm ordersForm = new OrdersForm();
        model.addAttribute("ordersform", ordersForm);
        log.info("/CreateOrders was called");
        return modelAndView;
    }

}
