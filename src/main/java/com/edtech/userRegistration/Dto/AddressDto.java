package com.edtech.userRegistration.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotBlank(message = "House number is required")
    private String houseNumber;

    @NotBlank(message = "Village or Mohalla is required")
    private String villageMohalla;

    @NotBlank(message = "PostOffice is required")
    private String postOffice;

    @NotBlank(message = "Police Station is required")
    private String policeStation;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "District is required")
    private String district;

    @Pattern(regexp = "\\d{6}", message = "Invalid Pincode")
    private String pincode;

    @NotBlank(message = "State is required")
    private String state;

}
