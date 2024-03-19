package com.example.project_hotel.service.impl;

import com.example.project_hotel.models.Hotel;
import com.example.project_hotel.repos.HotelRepository;
import com.example.project_hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public  HotelServiceImpl(HotelRepository hotelRepository)
    {
        this.hotelRepository=hotelRepository;
    }
    @Override
    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).get();
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> searchByLocation(String query) {
        List<Hotel> hotels=hotelRepository.searchHotelsByLocation(query);
        return hotels;
    }
}
