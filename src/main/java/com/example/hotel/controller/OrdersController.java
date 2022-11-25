package com.example.hotel.controller;

import com.example.hotel.forms.OrdersForm;
import com.example.hotel.services.OrdersServices;
import com.example.hotel.services.ServiceServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping
public class OrdersController {
    OrdersServices ordersServices;
    ServiceServices serviceServices;
    @GetMapping(value = {"/CreateOrders/{id}"})
    public ModelAndView CreateOrders(Model model, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("CreateOrders");
        OrdersForm ordersForm = new OrdersForm();
        ordersForm.setService(serviceServices.findService(id));
        model.addAttribute("ordersform", ordersForm);
        log.info("/CreateOrders was called");
        return modelAndView;
    }

}
