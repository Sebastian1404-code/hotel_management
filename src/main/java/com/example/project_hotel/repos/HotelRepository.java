package com.example.project_hotel.repos;

import com.example.project_hotel.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query("Select h from Hotel h where h.location LIKE CONCAT('%', :query, '%' )")
    List<Hotel> searchHotelsByLocation(String query);
}
