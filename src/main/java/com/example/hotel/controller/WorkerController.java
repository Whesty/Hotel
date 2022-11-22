package com.example.hotel.controller;
import com.example.hotel.forms.WorkerForm;
import com.example.hotel.model.Worker;
import com.example.hotel.services.WorkerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@ComponentScan("com.example.hotel.repository")
public class WorkerController {
    public final WorkerServices workerServices;
    public WorkerController(WorkerServices workerServices) {
        this.workerServices = workerServices;
    }
    @GetMapping("/ViewWorkers")
    public ModelAndView workers(ModelAndView modelAndView){
        List<Worker> workers = workerServices.findAll();
        modelAndView.addObject("workers", workers);
        modelAndView.setViewName("ViewWorkers");
        return modelAndView;
    }
    //Добавление сотрудника
    @GetMapping("/CreateWorker")
    public ModelAndView createWorker(ModelAndView modelAndView){
        modelAndView.setViewName("CreateWorker");
        WorkerForm workerForm = new WorkerForm();
        modelAndView.addObject("workerform", workerForm);
        return modelAndView;
    }
    @PostMapping("/CreateWorker")
    public ModelAndView createWorker(ModelAndView modelAndView, @ModelAttribute("workerform") WorkerForm workerForm){
        if (workerForm.getFirstname() != null  && workerForm.getSecendname() != null && workerForm.getLastname() != null && workerForm.getPosition() != null && workerForm.getPhone() != null && workerForm.getEmail() != null) {
            Worker newWorker = workerForm.toWorker();
            workerServices.save(newWorker);
        }
        modelAndView.setViewName("ViewWorkers");
        modelAndView.addObject("workers", workerServices.findAll());
        return modelAndView;
    }
    //Изменение сотрудника
    @GetMapping("/EditWorker/{id}")
    public ModelAndView editWorker(ModelAndView modelAndView, @PathVariable String id){
        modelAndView.setViewName("EditWorker");
        Worker worker = workerServices.findById(Integer.parseInt(id));
        WorkerForm workerForm = new WorkerForm(worker);
        modelAndView.addObject("workerform", workerForm);
        return modelAndView;
    }
    @PostMapping("/EditWorker")
    public ModelAndView editWorker(ModelAndView modelAndView, @ModelAttribute("workerform") WorkerForm workerForm){
        String id = String.valueOf(workerForm.getId());

        if (id != null && workerForm.getFirstname() != null  && workerForm.getLastname() != null && workerForm.getSecendname() != null && workerForm.getPosition() != null && workerForm.getPhone() != null && workerForm.getEmail() != null) {
            Worker newWorker = workerForm.toWorker();
            workerServices.save(newWorker);
        }
        modelAndView.setViewName("ViewWorkers");
        modelAndView.addObject("workers", workerServices.findAll());
        return modelAndView;
    }
    //Удаление сотрудника
    @GetMapping("/DeleteWorker/{id}")
    public ModelAndView deleteWorker(ModelAndView modelAndView, @PathVariable String id){

        workerServices.delete(Integer.parseInt(id));
        modelAndView.setViewName("ViewWorkers");
        modelAndView.addObject("workers", workerServices.findAll());
        return modelAndView;
    }


}
