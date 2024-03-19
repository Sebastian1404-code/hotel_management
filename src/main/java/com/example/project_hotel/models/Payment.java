package com.example.project_hotel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    /*@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
           message="Must be formatted MM/YY")*/
    private String ccExpiration;
    /*@Digits(integer = 3,fraction = 0,message = "Invalid CVV")*/
    private String ccCVV;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity userModel;

}
