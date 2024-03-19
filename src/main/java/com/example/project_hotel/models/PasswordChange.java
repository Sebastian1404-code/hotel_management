package com.example.project_hotel.models;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordChange {
    @NotEmpty
    private String password_new;
    @NotEmpty
    private String repeat_password;

}
