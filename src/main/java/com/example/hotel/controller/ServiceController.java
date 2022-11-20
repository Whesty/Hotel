package com.example.hotel.controller;

import com.example.hotel.forms.ServiceForm;
import com.example.hotel.model.Service;
import com.example.hotel.services.ServiceServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@ComponentScan("com.example.hotel.repository")
public class ServiceController {
    public final ServiceServices serviceServices;

    public ServiceController(ServiceServices serviceServices) {
        this.serviceServices = serviceServices;
    }
    //Создание услуги
    @GetMapping("/CreateService")
    public ModelAndView createService(Model model) {
        ModelAndView modelAndView = new ModelAndView("CreateService");
        ServiceForm serviceForm = new ServiceForm();
        model.addAttribute("serviceform", serviceForm);
        return modelAndView;
    }
    @PostMapping("/CreateService")
    public ModelAndView CreateService(Model model, @ModelAttribute ServiceForm serviceForm) {
        ModelAndView modelAndView = new ModelAndView("CreateService");
        log.info(createService(serviceForm));
        modelAndView.setViewName("ViewService");
        modelAndView.addObject("services", serviceServices.getServices());
        return modelAndView;
    }
    //Функиця создания услуг
    public String createService(ServiceForm serviceForm) {
        List<Service> services = serviceServices.getServices();
        if(serviceForm.getService_name() != null && serviceForm.getPrice() != 0 && serviceForm.getInfo() != null) {
            Service service = new Service(services.size(), serviceForm.getService_name(), serviceForm.getInfo(), serviceForm.getPrice());
            if(services.equals(service)) {
                return "Service already exists";
            }
            serviceServices.saveService(service);
            return "CreateService";
        }
        return "Service not created";
    }
    //Удаление услуги
@GetMapping("/DeleteService/{id}")
public ModelAndView deleteService(Model model, @PathVariable String id) {
    ModelAndView modelAndView = new ModelAndView("DeleteService");
    serviceServices.deleteService(Integer.parseInt(id));
    modelAndView.setViewName("ViewService");
    modelAndView.addObject("services", serviceServices.getServices());
    return modelAndView;
}
    //Просмотр услуг
    @GetMapping("/ViewService")
    public ModelAndView viewService(Model model) {
        ModelAndView modelAndView = new ModelAndView("ViewService");
        List<Service> services = serviceServices.getServices();
        model.addAttribute("services", services);
        return modelAndView;
    }
    //Редактирование услуги
    @GetMapping("/EditService/{id}")
    public ModelAndView editService(Model model, @PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("EditService");
        Service service = serviceServices.findService(Integer.parseInt(id));
        ServiceForm serviceForm = new ServiceForm();
        serviceForm.setId(service.getId());
        serviceForm.setService_name(service.getService_name());
        serviceForm.setInfo(service.getInfo());
        serviceForm.setPrice(service.getPrice());
        model.addAttribute("serviceform", serviceForm);
        return modelAndView;
    }
    @PostMapping("/EditService")
    public ModelAndView editService(Model model, @ModelAttribute ServiceForm serviceForm) {
        ModelAndView modelAndView = new ModelAndView("EditService");
        Service service = serviceServices.findService(serviceForm.getId());
        service.setService_name(serviceForm.getService_name());
        service.setInfo(serviceForm.getInfo());
        service.setPrice(serviceForm.getPrice());
        serviceServices.saveService(service);
        modelAndView.setViewName("ViewService");
        modelAndView.addObject("services", serviceServices.getServices());
        return modelAndView;
    }

}
