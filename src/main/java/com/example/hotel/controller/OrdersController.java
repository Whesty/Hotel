package com.example.hotel.controller;

import com.example.hotel.forms.OrdersForm;
import com.example.hotel.model.Orders;
import com.example.hotel.services.GuestServices;
import com.example.hotel.services.OrdersServices;
import com.example.hotel.services.ServiceServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@Slf4j
@RequestMapping
public class OrdersController {
    private final OrdersServices ordersServices;
    private final ServiceServices serviceServices;
    private final GuestServices guestServices;

    public OrdersController(OrdersServices ordersServices, ServiceServices serviceServices, GuestServices guestServices) {
        this.ordersServices = ordersServices;
        this.serviceServices = serviceServices;
        this.guestServices = guestServices;
    }

    @GetMapping(value = {"/CreateOrders/{id}"})
    public ModelAndView CreateOrders(Model model, @PathVariable Integer id){
        try {
            ModelAndView modelAndView = new ModelAndView("CreateOrders");
            OrdersForm ordersForm = new OrdersForm();
            ordersForm.setService(serviceServices.findService(id));
            model.addAttribute("ordersform", ordersForm);
            log.info("/CreateOrders was called");
            return modelAndView;
        } catch (Exception err){
            model.addAttribute("errorMessage", err.getMessage());
            return new ModelAndView("redirect:/ViewOrders");
        }
    }
    @PostMapping(value = {"/CreateOrders"})
    public ModelAndView CreateOrders(Model model, OrdersForm ordersForm){
        ModelAndView modelAndView = new ModelAndView("index");
        Orders order = new Orders();
        ordersForm.setGuest(guestServices.findGuest(ordersForm.getGuest().getId()));
        order.setGuest(ordersForm.getGuest());
        order.setService(ordersForm.getService());
        //Получить настоящую дату
        Date date = new Date();
        order.setDate_orders(date);
        ordersServices.saveOrders(order);
        log.info("/CreateOrders was called");
        return modelAndView;
    }
    @GetMapping(value = {"/ViewOrders"})
    public ModelAndView ViewOrders(Model model){
        ModelAndView modelAndView = new ModelAndView("ViewOrders");
        model.addAttribute("orders", ordersServices.getOrders());
        log.info("/ViewOrders was called");
        return modelAndView;
    }
    @PostMapping(value = {"/SetWorker/{id}"})
    public ModelAndView SetWorker(Model model, @PathVariable int id){
        try {
            ModelAndView modelAndView = new ModelAndView("ViewOrders");
            Orders order = ordersServices.findOrders(id);
            //Получить настоящую дату
            Date date = new Date();
            order.setDate_service(date);
            //Установить работника
            //order.setWorker(ThisUser.getWorker());
            ordersServices.saveOrders(order);
            log.info("/SetWorker was called");
            return modelAndView;
        } catch (Exception err){
            model.addAttribute("errorMessage", err.getMessage());
            return new ModelAndView("redirect:/index");
        }
    }

    //Изменить заказ
    @GetMapping(value = {"/EditOrders/{id}"})
    public ModelAndView EditOrders(Model model, @PathVariable Integer id){
        try {
            ModelAndView modelAndView = new ModelAndView("EditOrders");
            OrdersForm ordersForm = new OrdersForm();
            ordersForm.setService(serviceServices.findService(id));
            model.addAttribute("ordersform", ordersForm);
            log.info("/EditOrders was called");
            return modelAndView;
        } catch (Exception err){
            model.addAttribute("errorMessage", err.getMessage());
            return new ModelAndView("redirect:/ViewOrders");
        }
    }
    @PostMapping(value = {"/EditOrders"})
    public ModelAndView EditOrders(Model model, OrdersForm ordersForm){
        ModelAndView modelAndView = new ModelAndView("index");
        Orders order = ordersServices.findOrders(ordersForm.getId());
        ordersForm.setGuest(guestServices.findGuest(ordersForm.getGuest().getId()));
        order.setGuest(ordersForm.getGuest());
        order.setService(ordersForm.getService());
        order.setDate_orders(ordersForm.getDate_orders());
        ordersServices.saveOrders(order);
        log.info("/EditOrders was called");
        return modelAndView;
    }
    //Удалить заказ
    @GetMapping(value = {"/DeleteOrders/{id}"})
    public ModelAndView DeleteOrders(Model model, @PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("index");
        try{
        ordersServices.deleteOrders(id);
        log.info("/DeleteOrders was called");
        } catch (Exception err){
            model.addAttribute("errorMessage", err.getMessage());
        }
        return modelAndView;
    }
}
