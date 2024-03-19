package com.example.project_hotel.controllers;

import com.example.project_hotel.models.Hotel;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.Reservation;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.service.UserService;
import com.example.project_hotel.service.impl.ReservationServiceImpl;
import com.example.project_hotel.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.project_hotel.service.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"reservation","user"})
public class HotelController {

    private HotelServiceImpl hotelService;
    private ReservationServiceImpl reservationService;
    private UserServiceImpl userService;

    @Autowired
    public HotelController(HotelServiceImpl hotelService,ReservationServiceImpl reservationService,UserServiceImpl userService)
    {
        this.hotelService = hotelService;
        this.reservationService=reservationService;
        this.userService=userService;
    }

    @GetMapping("/hotel")
    public String display_all(Model model, HttpSession session) {
        RegistrationDto user = (RegistrationDto) session.getAttribute("user");
        if (user == null) {
            System.out.println("User is not logged");
            return "redirect:/login";
        }
        else
        {
            List<Hotel> hotels = hotelService.findAllHotels();
            model.addAttribute("hotels", hotels);
            return "hotel-list";
        }

    }

    @GetMapping("/hotel/{hotelId}")
    public String display_one(@PathVariable("hotelId") Long hotelId, Model model) {
        Hotel hotel = hotelService.findHotelById(hotelId);
        model.addAttribute("hotel", hotel);
        return "hotel-details";
    }

   /* @GetMapping("/hotel/new")
    public String create_hotel(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "hotel-create";
    }

    @PostMapping("/hotel/new")
    public String validate_new_hotel(@Valid @ModelAttribute Hotel hotel, Errors errors) {
        if (errors.hasErrors()) {
            return "hotel-create";
        }
        hotelService.save(hotel);
        return "redirect:/hotel";
    }
*/

    @GetMapping("/hotels/search")
    public String fetch_by_location(@RequestParam(value = "query") String location ,Model model) {
        List<Hotel> hotels= hotelService.searchByLocation(location);
        model.addAttribute("hotels",hotels);
        return "hotel-location";
    }

    //de aici incolo

    @GetMapping("hotels/location/{hotelLocation}")
    public String hotelsByLocation(@PathVariable("hotelLocation") String location,Model model)
    {
        List<Hotel> hotels= hotelService.searchByLocation(location);
        model.addAttribute("hotels",hotels);
        return "choose-hotel";
    }


    @GetMapping("hotels/this/{hotelId}")
    public String getReservationId(@PathVariable("hotelId") Long Id, @SessionAttribute("reservation") Reservation reservation, @SessionAttribute("user") RegistrationDto user)
    {
        UserEntity existingUser=userService.findByUsername(user.getUsername());
        if (existingUser==null)
        {
            return "redirect:/login";
        }
        reservation.setUserEntity(existingUser);

        Hotel hotel=hotelService.findHotelById(Id);
        if(hotel!=null)
        {
            reservation.setHotel(hotel);
            reservationService.save(reservation);
            //sessionStatus.setComplete();
            return "redirect:/payment";
        }
        else return "redirect:/register";
    }



}
