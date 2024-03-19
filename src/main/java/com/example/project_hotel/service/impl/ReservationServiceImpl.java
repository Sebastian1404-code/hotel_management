package com.example.project_hotel.service.impl;


import com.example.project_hotel.models.Reservation;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.repos.ReservationRepository;
import com.example.project_hotel.repos.UserRepository;
import com.example.project_hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,UserRepository userRepository)
    {
        this.reservationRepository=reservationRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        //Optional<UserEntity> userEntity=userRepository.findById(userId);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> searchReservationByUser(UserEntity userEntity) {
        return reservationRepository.searchReservationByUser(userEntity);
    }

    @Override
    public Optional<Reservation> findById(Long Id) {
        return reservationRepository.findById(Id);
    }
}
