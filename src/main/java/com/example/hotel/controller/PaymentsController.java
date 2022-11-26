package com.example.hotel.controller;

import com.example.hotel.forms.PaymentsForm;
import com.example.hotel.model.Payments;
import com.example.hotel.services.PaymentsServices;
import com.example.hotel.services.WorkerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping
public class PaymentsController {
    private final PaymentsServices paymentsServices;
    private final WorkerServices workerServices;

    public PaymentsController(PaymentsServices paymentsServices, WorkerServices workerServices) {
        this.paymentsServices = paymentsServices;
        this.workerServices = workerServices;
    }

    // Список выплат
    @GetMapping(value = {"/ViewPayments"})
    public ModelAndView ViewPayments(Model model){
        ModelAndView modelAndView = new ModelAndView("ViewPayments");
        model.addAttribute("payments", paymentsServices.getPayments());
        log.info("/ViewPayments was called");
        return modelAndView;
    }

    // Добавить выплату
    @GetMapping(value = {"/CreatePayments"})
    public ModelAndView CreatePayments(Model model){
        ModelAndView modelAndView = new ModelAndView("CreatePayments");
        PaymentsForm paymentsForm = new PaymentsForm();
        model.addAttribute("paymentsform", paymentsForm);
        log.info("/CreatePayments was called");
        return modelAndView;
    }
    @PostMapping(value = {"/CreatePayments"})
    public ModelAndView CreatePayments(Model model,@ModelAttribute("paymentsform") PaymentsForm paymentsForm){
        ModelAndView modelAndView = new ModelAndView("index");
        if (paymentsForm != null) {
            Payments payments = new Payments();
            payments.setPrize(paymentsForm.getPrize());
            payments.setWorker(workerServices.findById(paymentsForm.getWorker()));
            payments.setWork_hour(paymentsForm.getWork_hour());
            payments.setSalary(paymentsForm.getSalary());
            paymentsServices.savePayments(payments);
            log.info(payments.toString());
            return modelAndView;
        }
        log.info("/CreatePayments was called");
        return modelAndView;
    }
    // Удалить выплату
    // Изменить выплату

}
