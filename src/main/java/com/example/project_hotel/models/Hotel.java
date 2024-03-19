package com.example.project_hotel.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photoUrl;
    private String description;
    private int stars;
    private String location;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "hotel",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
