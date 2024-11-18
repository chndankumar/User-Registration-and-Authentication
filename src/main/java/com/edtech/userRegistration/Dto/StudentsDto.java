package com.edtech.userRegistration.Dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name size should be in Between 5-15")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank(message = "Father's name is required")
    @Size(min = 3, max = 20, message = "Name size should be in Between 5-15")
    private String fatherName;

    @NotBlank(message = "Mother's name is required")
    @Size(min = 3, max = 20, message = "Name size should be in Between 5-15")
    private String motherName;

    @NotBlank(message = "Guardian's name is required")
    @Size(min = 3, max = 20, message = "Name size should be in Between 5-15")
    private String guardianName;

    @Valid
    private AddressDto address;

    @Valid
    private AcademicDetailsDto academic;

}
