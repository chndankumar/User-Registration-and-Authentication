package com.edtech.userRegistration.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNumber;

    private String villageMohalla;

    private String postOffice;

    private String policeStation;

    private String city;

    private String district;

    private String pincode;

    private String state;

    // state and pin shold be validate by the fronend guys
}
