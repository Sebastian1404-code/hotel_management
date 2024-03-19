package com.example.project_hotel.service;

import com.example.project_hotel.models.Review;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewService {
    Review save(Review review);
    List<Review> review_hotel(Long Id);

    List<Review> reviewsUser(String username);

}
