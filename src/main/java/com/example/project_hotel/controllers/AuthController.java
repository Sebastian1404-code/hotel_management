package com.example.project_hotel.controllers;

import com.example.project_hotel.models.PasswordChange;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class AuthController {
    private UserServiceImpl userService;
    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public RegistrationDto setUpUser() {
        return new RegistrationDto();
    }

    @GetMapping("/changePassword")
    public String change(Model model)
    {
        PasswordChange passwordChange=new PasswordChange();
        model.addAttribute("PasswordChange",passwordChange);
        return "change-password";
    }

    @PostMapping("/changePassword")
    public String passwordChange(@Valid @ModelAttribute("PasswordChange") PasswordChange passwordChange, Errors errors,@SessionAttribute("user") RegistrationDto User)
    {
        if(errors.hasErrors())
        {
            return "change-password";
        }
        if(passwordChange.getPassword_new().equals(passwordChange.getRepeat_password()))
        {
            userService.changePassword(passwordChange,User);
        }

        return "redirect:/success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        /*RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);*/
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model)
    {
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "redirect:/register";
        }
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        userService.saveUser(user);
        return "redirect:/hotel?success";
    }



}

