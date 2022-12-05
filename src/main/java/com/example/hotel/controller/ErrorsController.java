package com.example.hotel.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorsController {
    @GetMapping("/401")
    public String error401(Model model) {
        model.addAttribute("message", "You are not authorized to view this page");
        return "401";
    }
}
