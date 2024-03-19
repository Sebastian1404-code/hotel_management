package com.example.project_hotel.controllers;


import com.example.project_hotel.models.Hotel;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.Reservation;
import com.example.project_hotel.models.Review;
import com.example.project_hotel.service.impl.HotelServiceImpl;
import com.example.project_hotel.service.impl.ReservationServiceImpl;
import com.example.project_hotel.service.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
@SessionAttributes("user")
public class ReviewController {

    private ReviewServiceImpl reviewService;
    private ReservationServiceImpl reservationService;
    private HotelServiceImpl hotelService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService, ReservationServiceImpl reservationService,HotelServiceImpl hotelService) {
        this.reviewService = reviewService;
        this.reservationService = reservationService;
        this.hotelService=hotelService;
    }

    @GetMapping("/{reviewId}")
    public String give_review(@PathVariable("reviewId") Long Id, Model model)
    {
        Review review=new Review();
        model.addAttribute("reservation_id",Id);
        model.addAttribute("review",review);
        return "review";
    }
    @PostMapping("/{reviewId}")
    public String process_review(@PathVariable("reviewId") Long Id,@Valid @ModelAttribute("review") Review review, Errors errors)
    {
        if(errors.hasErrors())
        {
            return "review";
        }
        Optional<Reservation> reservation=reservationService.findById(Id);
        if(reservation.isEmpty())
        {
            return "redirect:/review";

        }
        System.out.println(review.getStars());
        review.setReservation(reservation.get());
        reviewService.save(review);
        return "redirect:/reserve/reservations";
    }


    @GetMapping("/all/{hotelId}")
    public String display_reviews(@PathVariable("hotelId") Long Id,Model model)
    {
        List<Review> reviews=reviewService.review_hotel(Id);
        Hotel hotel=hotelService.findHotelById(Id);
        model.addAttribute("name",hotel.getName());
        model.addAttribute("reviews",reviews);
        return "review-hotel";
    }

    @GetMapping("/my_reviews")
    public String display_personal(@SessionAttribute("user") RegistrationDto user,Model model)
    {
        String username=user.getUsername();
        System.out.println(username);
        List<Review> reviews=reviewService.reviewsUser(username);
        model.addAttribute("reviews",reviews);
        return "my-reviews";
    }




}
