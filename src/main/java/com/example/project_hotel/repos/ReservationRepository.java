package com.example.project_hotel.repos;

import com.example.project_hotel.models.Reservation;
import com.example.project_hotel.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("Select r from Reservation r where r.userEntity=:userEntity")
    List<Reservation> searchReservationByUser(UserEntity userEntity);
}
