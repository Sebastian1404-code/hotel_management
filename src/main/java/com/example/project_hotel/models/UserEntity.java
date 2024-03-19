package com.example.project_hotel.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username is required")
    private String username;
    //@Email(message = "Invalid email")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;

    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="user_id",referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private List<Role> roles=new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "userModel",cascade = CascadeType.ALL)
    private List<Payment> payments;




}
