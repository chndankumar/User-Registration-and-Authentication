package com.edtech.userRegistration.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "Email should be not empty")
    @Email(message = "please enter the correct mail")
    String email;

    @NotBlank(message = "password should not be empty")
    String passord;
}
