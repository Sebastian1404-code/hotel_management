package com.example.project_hotel.service.impl;

import com.example.project_hotel.models.Review;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.repos.ReviewRepository;
import com.example.project_hotel.repos.UserRepository;
import com.example.project_hotel.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    private UserRepository userRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> review_hotel(Long Id) {
        return reviewRepository.reviewsHotel(Id);
    }

    @Override
    public List<Review> reviewsUser(String username) {
        UserEntity user=userRepository.findByUsername(username);
        return reviewRepository.reviewOfUser(user);
    }
}
