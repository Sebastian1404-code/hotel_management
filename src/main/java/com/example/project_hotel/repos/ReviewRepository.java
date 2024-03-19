package com.example.project_hotel.repos;

import com.example.project_hotel.models.Review;
import com.example.project_hotel.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r from Review r join Reservation r2 on r.reservation=r2 join Hotel h on h=r2.hotel where h.id= :Id")
    List<Review> reviewsHotel(Long Id);


    @Query("select r from Review r join Reservation r2 on r.reservation=r2 where r2.userEntity=:u")
    List<Review> reviewOfUser(UserEntity u);
}
