package com.example.project_hotel.controllers;


import com.example.project_hotel.models.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guest")
public class GuestController {
    @GetMapping("/complete-guest-details")
    public String completeGuestDetails(@ModelAttribute("reservationId") Long reservationId,
                                       @ModelAttribute("numberOfGuests") int numberOfGuests,
                                       Model model) {
        // Pass necessary data to the page for completing guest details
        model.addAttribute("reservationId", reservationId);
        model.addAttribute("numberOfGuests", numberOfGuests);

        // Add any additional data or logic needed for completing guest details

        return "complete-guest-details";
    }

    @PostMapping("/save-guest-details")
    public String saveGuestDetails(@ModelAttribute("reservationId") Long reservationId,
                                   @ModelAttribute("numberOfGuests") int numberOfGuests,
                                   @ModelAttribute("guestList") List<Guest> guestList) {
        // Process and save guest details here

        // Redirect to a success page or wherever is appropriate
        return "redirect:/";
    }
}
