package com.example.project_hotel.service;

import com.example.project_hotel.models.PasswordChange;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    Optional<UserEntity> findById(Long id);

    //void changePassword(RegistrationDto registrationDto);

    void changePassword(PasswordChange passwordChange, RegistrationDto registrationDto);
}
