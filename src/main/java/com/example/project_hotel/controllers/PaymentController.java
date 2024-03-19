package com.example.project_hotel.controllers;

import com.example.project_hotel.models.Payment;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.service.impl.PaymentServiceImpl;
import com.example.project_hotel.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@SessionAttributes("user")
public class PaymentController {
    private PaymentServiceImpl paymentService;
    private UserServiceImpl userService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService,UserServiceImpl userService) {
        this.userService=userService;
        this.paymentService = paymentService;
    }

    @GetMapping()
    public String payment_info(Model model)
    {
        Payment payment=new Payment();
        model.addAttribute("payment",payment);
        return "payment";
    }
    @PostMapping()
    public String verify(@Valid @ModelAttribute("payment") Payment payment, @SessionAttribute("user") RegistrationDto user, Errors errors)
    {
        if(errors.hasErrors())
        {
            return "payment";
        }
        payment.setUserModel(userService.findByUsername(user.getUsername()));
        paymentService.save(payment);
        return "/success";
    }
}
