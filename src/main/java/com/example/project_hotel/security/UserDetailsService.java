package com.example.project_hotel.security;


import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.project_hotel.models.UserEntity;

import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if(user != null) {
           /* User authUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );*/
            RegistrationDto authUser=mapToRegistrationDto(user);

            return new UserDetailsImpl(authUser);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
    private RegistrationDto mapToRegistrationDto(UserEntity userEntity) {
        // Implement your mapping logic here
        // For example, create a new RegistrationDto and set its properties based on userEntity
        return RegistrationDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserEntity findUserByUsername(String username)
    {
        return userRepository.findByUsername(username);

    }
}
