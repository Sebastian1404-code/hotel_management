package com.example.project_hotel.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/success")
    public String reservation_success()
    {
        return "success";
    }
}
