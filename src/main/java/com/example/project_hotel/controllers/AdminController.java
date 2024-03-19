package com.example.project_hotel.controllers;


import com.example.project_hotel.models.Hotel;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.service.impl.HotelServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
@RequestMapping("/admin")
public class AdminController {
    private HotelServiceImpl hotelService;


    @Autowired
    public AdminController(HotelServiceImpl hotelService)
    {
        this.hotelService=hotelService;
    }

    @GetMapping()
    public String home_admin(Model model, HttpSession session)
    {
        RegistrationDto userEnt= (RegistrationDto) session.getAttribute("user");
        model.addAttribute("user",userEnt);
        return "admin-page";
    }



    @GetMapping("hotel/new")
    public String admin_page(Model model)
    {
        Hotel hotel=new Hotel();
        model.addAttribute("hotel",hotel);
        return "hotel-create";

    }
    @PostMapping("/hotel/new")
    public String validate_new_hotel(@Valid @ModelAttribute Hotel hotel, Errors errors) {
        if (errors.hasErrors()) {
            return "hotel-create";
        }
        hotelService.save(hotel);
        return "redirect:/admin";
    }


}
