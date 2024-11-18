package com.edtech.userRegistration.Dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDetailsDto {

    @NotBlank(message = "School board for 10th is required")
    private String schoolBoard10;

    @Min(value = 0, message = "10th marks should be between 0 and 100")
    @Max(value = 100, message = "10th marks should be between 0 and 100")
    private Double schoolMarks10;

    @NotNull(message = "Passing year for 10th is required")
    //@Past(message = "Passing year for 10th should be in the past")
    private Integer passingYear10;

    @NotBlank(message = "School board for 12th is required")
    private String schoolBoard12;

    @Min(value = 0, message = "12th marks should be between 0 and 100")
    @Max(value = 100, message = "12th marks should be between 0 and 100")
    private Double schoolMarks12;

    @NotNull(message = "Passing year for 12th is required")
    private Integer passingYear12;

    @NotBlank(message = "College name is required")
    private String collegeName;

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Core subject is required")
    private String coreSubject;

    //@Positive(message = "College marks should be a positive number")
    @Min(value = 0, message = "College marks should be between 0 and 100")
    @Max(value = 100, message = "College marks should be between 0 and 100")
    private Double collegeMarks;

    @NotNull(message = "Passing year for college is required")
    private Integer passingYear;

}
