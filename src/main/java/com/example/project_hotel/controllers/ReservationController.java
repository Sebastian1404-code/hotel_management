package com.example.project_hotel.controllers;


import com.example.project_hotel.models.Hotel;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.Reservation;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.service.impl.HotelServiceImpl;
import com.example.project_hotel.service.impl.ReservationServiceImpl;
import com.example.project_hotel.service.impl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/reserve")
@SessionAttributes({"user","reservation"})
public class ReservationController {

    private ReservationServiceImpl reservationService;
    private UserServiceImpl userService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationService,UserServiceImpl userService) {
        this.reservationService = reservationService;
        this.userService=userService;
    }

    @ModelAttribute("reservation")
    public Reservation setUpReservation() {
        return new Reservation();
    }

    @GetMapping()
    public String create_reservation(Model model)
    {
        /*Reservation reservation=new Reservation();
        model.addAttribute("reservation",reservation);*/
        return "reservation-create";
    }

    @PostMapping()
    public String save_reservation(@Valid @ModelAttribute("reservation") Reservation reservation,
                                   @SessionAttribute("user") RegistrationDto user, Errors errors) {
        if (errors.hasErrors()) {
            return "reservation-create";
        }
        /*int numberOfGuests = reservation.getAdults() + reservation.getChildren();
        model.addAttribute("numberOfGuests", numberOfGuests);
        model.addAttribute("reservationId", reservation.getId());  // Assuming you have an ID for the reservation
        return "redirect:/guest/complete-guest-details";*/
        return "redirect:/hotels/location/"+reservation.getLocation();
    }

    @GetMapping("/reservations")
    public String my_reservations(Model model,@SessionAttribute("user") RegistrationDto user)
    {
        List<Reservation> myReservations = reservationService.searchReservationByUser(mapperUser(user));
        model.addAttribute("reservations",myReservations);
        return "my-reservation-list";
    }

    public UserEntity mapperUser(RegistrationDto registrationDto)
    {
        UserEntity userEntity=UserEntity.builder()
                .id(registrationDto.getId())
                .username(registrationDto.getUsername())
                .password(registrationDto.getPassword())
                .email(registrationDto.getEmail())
                .build();
        return userEntity;
    }
}
