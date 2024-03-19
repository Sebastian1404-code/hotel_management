package com.example.project_hotel.service;

import com.example.project_hotel.models.Reservation;
import com.example.project_hotel.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation save(Reservation reservation);
    List<Reservation> searchReservationByUser(UserEntity userEntity);
    Optional<Reservation> findById(Long Id);
}
