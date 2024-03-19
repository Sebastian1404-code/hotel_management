package com.example.project_hotel.service.impl;

import com.example.project_hotel.models.PasswordChange;
import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.Role;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.repos.RoleRepository;
import com.example.project_hotel.repos.UserRepository;
import com.example.project_hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user=new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role=roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void changePassword(PasswordChange passwordChange,RegistrationDto registrationDto) {
        userRepository.change(passwordEncoder.encode(passwordChange.getPassword_new()),registrationDto.getUsername());
    }

}
