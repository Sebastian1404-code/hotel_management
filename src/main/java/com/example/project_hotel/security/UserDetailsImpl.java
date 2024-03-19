package com.example.project_hotel.security;

import com.example.project_hotel.models.RegistrationDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private final RegistrationDto registrationDto;

    public UserDetailsImpl(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
    }

    public RegistrationDto getRegistrationDto() {
        return registrationDto;
    }



        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // In a real application, you might fetch roles from the database and convert them to GrantedAuthority
        // For simplicity, let's assume every user has a ROLE_USER role
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return registrationDto.getPassword();
    }

    @Override
    public String getUsername() {
        return registrationDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
