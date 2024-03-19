package com.example.project_hotel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@Entity
//@Table("reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The name of hotel is required")
    private String location;
    @NotNull(message = "Check-in date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate check_in;
    @NotNull(message = "Check-in date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate check_out;

   /* @Transient
    @Min(value=1,message = "The number of nights is required")
    private int nr_nights;*/

    @Min(value=1,message = "The number of persons is required")
    private int adults;
    @Min(value=1,message = "The number of persons is required")
    private int children;
    @Min(value=1,message = "The number of persons is required")
    private int rooms;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id",referencedColumnName = "id")
    private Hotel hotel;

    @OneToOne(mappedBy = "reservation")
    private Review review;



}
