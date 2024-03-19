package com.example.project_hotel.service;

import com.example.project_hotel.models.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> findAllHotels();
    Hotel findHotelById(Long hotelId);

    Hotel save(Hotel hotel);

    List<Hotel> searchByLocation(String query);
}
